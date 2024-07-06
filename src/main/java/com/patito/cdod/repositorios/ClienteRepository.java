package com.patito.cdod.repositorios;

import com.patito.cdod.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public Optional<Cliente> findByEmail(String email);

    public Boolean existsByEmail(String email);
}
