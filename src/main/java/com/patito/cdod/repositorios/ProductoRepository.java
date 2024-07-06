package com.patito.cdod.repositorios;

import com.patito.cdod.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByHawa(String hawa);

    List<Producto> findByHawaIn(List<String> hawas);
}
