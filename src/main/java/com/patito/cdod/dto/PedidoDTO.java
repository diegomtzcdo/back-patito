package com.patito.cdod.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.patito.cdod.entities.extra.EstatusPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long pedidoId;
    private String clienteNombre;
    private String clienteDireccion;
    private String clienteCorreo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date fecha;
    private int cantidadArticulos;
    private EstatusPedido estatus;
}
