package com.patito.cdod.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TiendaPayload {
    private String nombre;
    private String direccion;
    private String telefono;
}
