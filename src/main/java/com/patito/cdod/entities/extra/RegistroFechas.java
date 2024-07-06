package com.patito.cdod.entities.extra;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class RegistroFechas {

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private Date fechaActualizacion;
}
