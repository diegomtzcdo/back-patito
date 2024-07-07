package com.patito.cdod.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.patito.cdod.entities.extra.EstatusPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDetalleDTO {
    private Long pedidoId;
    private ClientePayload cliente;
    private List<PedidoProductoDTO> productos;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date fecha;
    private EstatusPedido estatus;
}
