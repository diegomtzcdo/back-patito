package com.patito.cdod.servicios.implement;

import com.patito.cdod.dto.TiendaPayload;
import com.patito.cdod.entities.Tienda;
import com.patito.cdod.entities.Vendedor;
import com.patito.cdod.excepciones.ResourceNotFoundException;
import com.patito.cdod.repositorios.VendedorRepository;
import com.patito.cdod.servicios.TiendaService;
import com.patito.cdod.servicios.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendedorServiceImpl implements VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private TiendaService tiendaService;

    @Override
    public Vendedor getOrCreateVendedor(String nombre, String email, TiendaPayload tienda) {
        Vendedor vendedor;
        if(vendedorRepository.existsByEmail(email)) {
            vendedor = vendedorRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Vendedor", "email", email));
        } else {
            Tienda tiendaRef = tiendaService.getOrCreateVendedor(
                    tienda.getNombre(),
                    tienda.getDireccion(),
                    tienda.getTelefono());
            Vendedor newVendedor = Vendedor.builder()
                    .email(email)
                    .nombre(nombre)
                    .tienda(tiendaRef)
                    .build();
            vendedor = vendedorRepository.save(newVendedor);
        }
        return vendedor;
    }
}
