package com.patito.cdod.servicios;

import com.patito.cdod.dto.CustomResponseEntity;
import com.patito.cdod.dto.PedidoDTO;
import com.patito.cdod.dto.PedidoDetalleDTO;
import com.patito.cdod.dto.PedidoPayload;
import com.patito.cdod.entities.Pedido;
import com.patito.cdod.entities.extra.EstatusPedido;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PedidoService {
    public CustomResponseEntity<PedidoDTO> createPedido(PedidoPayload pedido, HttpServletRequest request, String email);
    public CustomResponseEntity<List<PedidoDTO>> listadoPedidos();
    public CustomResponseEntity<PedidoDTO> cambiarEstatus(Long id, EstatusPedido nuevoEstatus);
    public CustomResponseEntity<PedidoDetalleDTO> detallePedido(Long id);
}
