package com.patito.cdod.servicios;

import com.patito.cdod.dto.ClientePayload;
import com.patito.cdod.dto.CustomResponseEntity;
import com.patito.cdod.entities.Cliente;

public interface ClienteService {

    public Cliente obtenerClientePorEmail(String email);
    public CustomResponseEntity<Cliente> create(ClientePayload cliente);
    public CustomResponseEntity<Cliente> actualizar(ClientePayload cliente, String email);
    public CustomResponseEntity<String> borrar(String email);
}
