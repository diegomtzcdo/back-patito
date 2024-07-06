package com.patito.cdod.entities.extra;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public class Auditoria extends RegistroFechas {
    private String ipUsuario;
    private String accion;
}
