package com.patito.cdod.servicios.implement;

import com.patito.cdod.dto.ClientePayload;
import com.patito.cdod.dto.CustomResponseEntity;
import com.patito.cdod.entities.Cliente;
import com.patito.cdod.excepciones.BadRequestException;
import com.patito.cdod.excepciones.ResourceNotFoundException;
import com.patito.cdod.repositorios.ClienteRepository;
import com.patito.cdod.servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente obtenerClientePorEmail(String email) {
        return clienteRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "email", email));
    }

    @Override
    public CustomResponseEntity<Cliente> create(ClientePayload cliente) {
        if(clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new BadRequestException("Correo ya registrado, ingresa con otro");
        }
        Cliente newCliente = Cliente.builder()
                .email(cliente.getEmail())
                .password(cliente.getPassword())
                .nombre(cliente.getNombre())
                .direccion(cliente.getDireccion())
                .telefono(cliente.getTelefono())
                .build();
        return CustomResponseEntity.success200(clienteRepository.save(newCliente), "Cliente creado");
    }

    @Override
    public CustomResponseEntity<Cliente> actualizar(ClientePayload cliente, String email) {
        Cliente updateCliente = clienteRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "email", email));
        updateCliente.setPassword(cliente.getPassword());
        updateCliente.setDireccion(cliente.getDireccion());
        updateCliente.setTelefono(cliente.getTelefono());
        return CustomResponseEntity.customStatus(clienteRepository.save(updateCliente), "Cliente creado", HttpStatus.CREATED);
    }

    @Override
    public CustomResponseEntity<String> borrar(String email) {
        Cliente cliente = clienteRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "email", email));
        clienteRepository.delete(cliente);
        return CustomResponseEntity.success200(null, "Cliente eliminado");
    }

}
