package com.patito.cdod.servicios;

import com.patito.cdod.entities.Tienda;

public interface TiendaService {
    public Tienda getOrCreateVendedor(String nombre, String direccion, String telefono);
}
