package com.patito.cdod.entities;

import com.patito.cdod.entities.extra.RegistroFechas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Vendedor extends RegistroFechas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @NaturalId
    private String email;

    @ManyToOne
    @JoinColumn(name = "tienda_id")
    private Tienda tienda;

}
