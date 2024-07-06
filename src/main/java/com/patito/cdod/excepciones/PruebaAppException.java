package com.patito.cdod.excepciones;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PruebaAppException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    private HttpStatus estado;
    private String mensaje;

    public PruebaAppException(HttpStatus estado, String mensaje) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
    }

}
