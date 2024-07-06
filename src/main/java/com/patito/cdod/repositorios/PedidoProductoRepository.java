package com.patito.cdod.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.patito.cdod.entities.PedidoProducto;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoProductoRepository extends JpaRepository<PedidoProducto, Long> {
}

