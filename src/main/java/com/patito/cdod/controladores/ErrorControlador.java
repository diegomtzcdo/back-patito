package com.patito.cdod.controladores;

import java.util.Date;

import com.patito.cdod.dto.CustomResponseEntity;
import com.patito.cdod.dto.DetallesErrorDTO;
import com.patito.cdod.excepciones.AppException;
import com.patito.cdod.excepciones.BadRequestException;
import com.patito.cdod.excepciones.OperacionNoPermitidaExcepcion;
import com.patito.cdod.excepciones.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorControlador extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RuntimeException.class, AppException.class})
    public CustomResponseEntity<DetallesErrorDTO> handle(RuntimeException e, WebRequest request) {
        return CustomResponseEntity.customStatus(DetallesErrorDTO.builder().timestamp(new Date()).message(e.getMessage()).
                details(request.getDescription(true)).build(), "Error en el servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public CustomResponseEntity<DetallesErrorDTO> handle(ResourceNotFoundException e, WebRequest request) {
        return CustomResponseEntity.customStatus(DetallesErrorDTO.builder().timestamp(new Date()).message(e.getMessage()).
                details(request.getDescription(true)).build(), "No encontrado.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    public CustomResponseEntity<DetallesErrorDTO> handle(BadRequestException e, WebRequest request) {
        return CustomResponseEntity.customStatus(DetallesErrorDTO.builder().timestamp(new Date()).message(e.getMessage()).
                details(request.getDescription(true)).build(), "Error de formato.", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler({OperacionNoPermitidaExcepcion.class})
    public CustomResponseEntity<DetallesErrorDTO> handle(OperacionNoPermitidaExcepcion e, WebRequest request) {
        return CustomResponseEntity.customStatus(DetallesErrorDTO.builder().timestamp(new Date()).message(e.getMessage()).
                details(request.getDescription(true)).build(), "No Autorizado.", HttpStatus.UNAUTHORIZED);
    }
}