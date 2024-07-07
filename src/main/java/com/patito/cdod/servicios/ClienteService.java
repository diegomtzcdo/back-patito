package com.patito.cdod.servicios;

import com.patito.cdod.dto.ClientePayload;
import com.patito.cdod.dto.CustomResponseEntity;
import com.patito.cdod.entities.Cliente;

public interface ClienteService {

    Cliente obtenerClientePorEmail(String email);
    CustomResponseEntity<Cliente> create(ClientePayload cliente);
    CustomResponseEntity<Cliente> actualizar(ClientePayload cliente, String email);
    CustomResponseEntity<String> borrar(String email);
}
