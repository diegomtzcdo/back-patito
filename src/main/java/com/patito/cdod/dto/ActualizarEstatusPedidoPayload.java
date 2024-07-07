package com.patito.cdod.dto;

import com.patito.cdod.entities.extra.EstatusPedido;
import lombok.Data;

@Data
public class ActualizarEstatusPedidoPayload {
    private EstatusPedido nuevoEstatus;
}