package com.patito.cdod.entities;

import com.patito.cdod.entities.extra.Auditoria;
import com.patito.cdod.entities.extra.EstatusPedido;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
public class Pedido extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    @ManyToOne
    @JoinColumn(name = "tienda_id")
    private Tienda tienda;

    @Enumerated(EnumType.STRING)
    private EstatusPedido estatus;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<PedidoProducto> productos;
}
