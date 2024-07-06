package com.patito.cdod.entities.extra;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditListener.class)
public class RegistroFechas implements Auditable {

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private Date fechaActualizacion;

    @Override
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
