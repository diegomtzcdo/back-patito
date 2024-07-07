package com.patito.cdod.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoProductoPayload {
    @NotEmpty(message = "*Porfavor ingresa un HAWA")
    private String hawa;
    @Positive(message = "La cantidad debe ser positiva y mayor a 0")
    private Integer cantidad;
}
