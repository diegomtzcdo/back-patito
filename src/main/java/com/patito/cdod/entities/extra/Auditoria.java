package com.patito.cdod.entities.extra;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public class Auditoria {

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private Date fechaActualizacion;

    private String ipUsuario;
    private String accion;
}
