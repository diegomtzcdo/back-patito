package com.patito.cdod.repositorios;

import com.patito.cdod.entities.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Long> {
    public Optional<Tienda> findByNombre(String nombre);
    public Boolean existsByNombre(String nombre);
}
