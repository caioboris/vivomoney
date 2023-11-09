package com.vivomoney.exceptions;

import com.vivomoney.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity invalidPayloadEntry(IllegalArgumentException ex){
        ExceptionDTO exceptionDTO = new ExceptionDTO(400, "Dados inválidos! " + ex.getMessage());
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFound(EntityNotFoundException ex){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity duplicatedEntry(DataIntegrityViolationException ex){
        ExceptionDTO exceptionDTO = new ExceptionDTO(400, "Cliente já cadastrado! "+ ex.getMessage());
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity generalException(Exception ex){
        ExceptionDTO exceptionDTO = new ExceptionDTO(500,ex.getMessage());
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }


}
