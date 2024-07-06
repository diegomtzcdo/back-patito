package com.patito.cdod.entities.extra;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

public class AuditListener {

    @PrePersist
    public void setCreationDate(RegistroFechas auditable) {
        Date now = new Date();
        auditable.setFechaCreacion(now);
        auditable.setFechaActualizacion(now);
    }

    @PreUpdate
    public void setChangeDate(RegistroFechas auditable) {
        auditable.setFechaActualizacion(new Date());
    }
}
