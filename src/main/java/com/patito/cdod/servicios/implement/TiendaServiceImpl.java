package com.patito.cdod.servicios.implement;

import com.patito.cdod.entities.Tienda;
import com.patito.cdod.excepciones.ResourceNotFoundException;
import com.patito.cdod.repositorios.TiendaRepository;
import com.patito.cdod.servicios.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TiendaServiceImpl implements TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;

    @Override
    public Tienda getOrCreateVendedor(String nombre, String direccion, String telefono) {
        Tienda tienda;
        if(tiendaRepository.existsByNombre(nombre)) {
            tienda = tiendaRepository.findByNombre(nombre)
                    .orElseThrow(() -> new ResourceNotFoundException("Tienda", "nombre", nombre));
        } else {
            Tienda newTienda = Tienda.builder()
                    .nombre(nombre)
                    .direccion(direccion)
                    .telefono(telefono)
                    .build();
            tienda = tiendaRepository.save(newTienda);
        }
        return tienda;
    }
}
