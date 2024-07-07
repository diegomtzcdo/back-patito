package com.patito.cdod.controladores;

import com.patito.cdod.dto.*;
import com.patito.cdod.servicios.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/crear")
    @Secured("ROLE_CLIENTE")
    @ResponseBody
    public CustomResponseEntity<PedidoDTO> crearPedido(@Valid @RequestBody PedidoPayload pedido, HttpServletRequest request, Principal principal) {
        return pedidoService.createPedido(pedido, request, principal.getName());
    }

    @GetMapping("/todos")
    @ResponseBody
    public CustomResponseEntity<List<PedidoDTO>> obtenerTodosLosPedidos() {
        return pedidoService.listadoPedidos();
    }

    @PostMapping("/cambiar-estatus/{id}")
    @ResponseBody
    public CustomResponseEntity<PedidoDTO> cambiarEstatus(@PathVariable("id") Long id, @RequestBody ActualizarEstatusPedidoPayload actualizar) {
        return pedidoService.cambiarEstatus(id, actualizar.getNuevoEstatus());
    }

    @GetMapping("/detalle/{id}")
    public CustomResponseEntity<PedidoDetalleDTO> obtenerDetalle(@PathVariable("id") Long id) {
        return pedidoService.detallePedido(id);
    }

}
