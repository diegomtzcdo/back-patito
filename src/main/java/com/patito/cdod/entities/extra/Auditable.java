package com.patito.cdod.entities.extra;

import java.util.Date;

public interface Auditable {
    void setFechaCreacion(Date fechaCreacion);
    void setFechaActualizacion(Date fechaActualizacion);
}
