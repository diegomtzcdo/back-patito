package com.patito.cdod.repositorios;

import com.patito.cdod.entities.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    public Optional<Vendedor> findByEmail(String email);
    public Boolean existsByEmail(String email);
}
