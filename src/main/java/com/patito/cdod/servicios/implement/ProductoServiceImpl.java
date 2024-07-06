package com.patito.cdod.servicios.implement;

import com.patito.cdod.dto.CustomResponseEntity;
import com.patito.cdod.dto.ProductoDetallePayload;
import com.patito.cdod.dto.ProductoPayload;
import com.patito.cdod.dto.TiendaPayload;
import com.patito.cdod.entities.Producto;
import com.patito.cdod.entities.Vendedor;
import com.patito.cdod.excepciones.ResourceNotFoundException;
import com.patito.cdod.repositorios.ProductoRepository;
import com.patito.cdod.servicios.ProductoService;
import com.patito.cdod.servicios.VendedorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {
    private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private VendedorService vendedorService;

    @Override
    public CustomResponseEntity<List<ProductoPayload>> getAllProducts() {
        List<Producto> productos = productoRepository.findAll();
        List<ProductoPayload> productoPayloads = productos.stream()
                .map(this::convertirAProductoPayload)
                .collect(Collectors.toList());
        return CustomResponseEntity.success200(productoPayloads, "Lista de productos obtenida exitosamente");
    }

    @Override
    public CustomResponseEntity<ProductoPayload> crearProducto(ProductoDetallePayload productoPayload) {
        Vendedor vendedor = vendedorService.getOrCreateVendedor(
                productoPayload.getNombreVendedor(),
                productoPayload.getContactoVendedor(),
                productoPayload.getTienda());
        Producto nuevoProducto = Producto.builder()
                .hawa(productoPayload.getHawa())
                .nombre(productoPayload.getNombre())
                .precio(productoPayload.getPrecio())
                .existencias(productoPayload.getExistencias())
                .porcentajeDescuento(productoPayload.getPorcentajeDescuento())
                .vendedor(vendedor)
                .build();
        log.debug("nuevo producto: {}", nuevoProducto);
        Producto productoGuardado = productoRepository.save(nuevoProducto);
        return CustomResponseEntity.success200(convertirAProductoPayload(productoGuardado), "Producto creado exitosamente");
    }

    @Override
    public CustomResponseEntity<ProductoDetallePayload> getProductoDetalle(String hawa) {
        Producto producto = productoRepository.findByHawa(hawa)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "HAWA", hawa));
        return CustomResponseEntity.success200(convertirAProductoPayloadDetalle(producto), "Producto encontrado");
    }

    @Override
    public CustomResponseEntity<List<ProductoDetallePayload>> getListProductoDetalle(List<String> listHAWA) {
        List<Producto> productos = productoRepository.findByHawaIn(listHAWA);
        List<ProductoDetallePayload> productoPayloads = productos.stream()
                .map(this::convertirAProductoPayloadDetalle)
                .collect(Collectors.toList());
        return CustomResponseEntity.success200(productoPayloads, "Productos encontrados");
    }

    private ProductoPayload convertirAProductoPayload(Producto producto) {
        return ProductoPayload.builder()
                .hawa(producto.getHawa())
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .existencias(producto.getExistencias())
                .porcentajeDescuento(producto.getPorcentajeDescuento())
                .build();
    }

    private ProductoDetallePayload convertirAProductoPayloadDetalle(Producto producto) {
        return ProductoDetallePayload.builder()
                .hawa(producto.getHawa())
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .existencias(producto.getExistencias())
                .porcentajeDescuento(producto.getPorcentajeDescuento())
                .nombreVendedor(producto.getVendedor().getNombre())
                .contactoVendedor(producto.getVendedor().getEmail())
                .tienda(TiendaPayload.builder()
                        .nombre(producto.getVendedor().getTienda().getNombre())
                        .direccion(producto.getVendedor().getTienda().getDireccion())
                        .telefono(producto.getVendedor().getTienda().getTelefono())
                        .build())
                .build();
    }
}
