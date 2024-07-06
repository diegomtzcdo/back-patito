package com.patito.cdod.servicios;

import com.patito.cdod.dto.TiendaPayload;
import com.patito.cdod.entities.Vendedor;

public interface VendedorService {
    public Vendedor getOrCreateVendedor(String nombre, String email, TiendaPayload tienda);
}
