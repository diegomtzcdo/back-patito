package com.patito.cdod.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoPayload {
    private String hawa;
    private String nombre;
    private Float precio;
    private Integer existencias;
    private  Float porcentajeDescuento;
}
