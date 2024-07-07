package com.patito.cdod.servicios.implement;

import com.patito.cdod.dto.*;
import com.patito.cdod.entities.Cliente;
import com.patito.cdod.entities.Pedido;
import com.patito.cdod.entities.PedidoProducto;
import com.patito.cdod.entities.Producto;
import com.patito.cdod.excepciones.BadRequestException;
import com.patito.cdod.excepciones.ResourceNotFoundException;
import com.patito.cdod.servicios.PedidoService;
import com.patito.cdod.entities.extra.EstatusPedido;
import com.patito.cdod.repositorios.PedidoRepository;
import com.patito.cdod.servicios.ClienteService;
import com.patito.cdod.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductoService productoService;

    @Override
    public CustomResponseEntity<PedidoDTO> createPedido(PedidoPayload pedido, HttpServletRequest request, String email) {
        Cliente cliente = clienteService.obtenerClientePorEmail(email);
        Pedido newPedido = Pedido.builder()
                .estatus(EstatusPedido.PENDIENTE)
                .cliente(cliente)
                .ipUsuario(request.getRemoteAddr())
                .accion("Creacion Pedido")
                .build();
        new PedidoProductoPayload().getHawa();
        List<PedidoProducto> pedidoProductos = pedido.getProductos().stream()
                .map(pp -> {
                    Producto producto = productoService.obtenerProductoPorHawa(pp.getHawa());
                    return PedidoProducto.builder()
                            .pedido(newPedido)
                            .producto(producto)
                            .cantidad(pp.getCantidad())
                            .build();
                }).collect(Collectors.toList());

        newPedido.setProductos(pedidoProductos);
        Pedido pedidoGuardado = pedidoRepository.save(newPedido);

        return CustomResponseEntity.success200(convertirAPedidoDTO(pedidoGuardado), "Pedido creado exitosamente");
    }

    @Override
    public CustomResponseEntity<List<PedidoDTO>> listadoPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoDTO> pedidoDTOS = pedidos.stream()
                .map(this::convertirAPedidoDTO)
                .collect(Collectors.toList());
        return CustomResponseEntity.success200(pedidoDTOS, "Lista de pedidos obtenidos exitosamente.");
    }

    @Override
    public CustomResponseEntity<PedidoDTO> cambiarEstatus(Long id, EstatusPedido nuevoEstatus) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido", "id", id));
        if (pedido.getEstatus() == EstatusPedido.PENDIENTE) {
            if (nuevoEstatus == EstatusPedido.CANCELADO) {
                LocalDateTime fechaCreacion = LocalDateTime.ofInstant(pedido.getFechaCreacion().toInstant(), ZoneId.systemDefault());
                Duration duration = Duration.between(fechaCreacion, LocalDateTime.now());
                if (duration.toMinutes() > 10) {
                    throw new BadRequestException("El pedido solo se puede cancelar dentro de los 10 minutos de su creación");
                }
            }
            pedido.setEstatus(nuevoEstatus);
        } else {
            throw new BadRequestException("El pedido solo se puede actualizar si esta en estado PENDIENTE");
        }
        PedidoDTO pedidoActualizado = convertirAPedidoDTO(pedidoRepository.save(pedido));
        return CustomResponseEntity.success200(pedidoActualizado, "Estatus actualizado correctamente");
    }

    @Override
    public CustomResponseEntity<PedidoDetalleDTO> detallePedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido", "id", id));
        PedidoDetalleDTO pedidoDetalleDTO = convertirAPedidoDetalleDTO(pedido);
        return CustomResponseEntity.success200(pedidoDetalleDTO, "Detalle del pedido obtenido exitosamente");
    }

    private PedidoDetalleDTO convertirAPedidoDetalleDTO(Pedido pedido) {
        List<PedidoProductoDTO> productoPayloads = pedido.getProductos().stream()
                .map(this::convertirAPedidoProductoDTO)
                .collect(Collectors.toList());
        return PedidoDetalleDTO.builder()
                .pedidoId(pedido.getId())
                .cliente(ClientePayload.builder()
                        .email(pedido.getCliente().getEmail())
                        .password(null)
                        .nombre(pedido.getCliente().getNombre())
                        .direccion(pedido.getCliente().getDireccion())
                        .telefono(pedido.getCliente().getTelefono())
                        .build())
                .fecha(pedido.getFechaCreacion())
                .productos(productoPayloads)
                .estatus(pedido.getEstatus())
                .build();
    }

    private PedidoDTO convertirAPedidoDTO(Pedido pedido) {
        return PedidoDTO.builder()
                .pedidoId(pedido.getId())
                .clienteNombre(pedido.getCliente().getNombre())
                .clienteDireccion(pedido.getCliente().getDireccion())
                .clienteCorreo(pedido.getCliente().getEmail())
                .cantidadArticulos(pedido.getProductos().size())
                .fecha(pedido.getFechaCreacion())
                .estatus(pedido.getEstatus())
                .build();
    }

    private PedidoProductoDTO convertirAPedidoProductoDTO(PedidoProducto pedidoProducto) {
        return  PedidoProductoDTO.builder()
                .producto(productoService.convertirAProductoPayload(pedidoProducto.getProducto()))
                .cantidad(pedidoProducto.getCantidad())
                .build();
    }
}
