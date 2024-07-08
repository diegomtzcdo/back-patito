package com.patito.cdod.repositorios;

import com.patito.cdod.entities.extra.EstatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import com.patito.cdod.entities.PedidoProducto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoProductoRepository extends JpaRepository<PedidoProducto, Long> {
    @Query("SELECT SUM(pp.cantidad) FROM PedidoProducto pp WHERE pp.producto.id = :productoId AND pp.pedido.estatus = :estatus")
    Integer sumCantidadByProductoIdAndPedidoEstatus(@Param("productoId") Long productoId, @Param("estatus") EstatusPedido estatus);
}

