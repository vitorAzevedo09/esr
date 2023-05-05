package com.algaworks.algafoodapi.api.exceptionhandler;

import java.time.LocalDateTime;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * ApiExceptionHandler
 */
@ControllerAdvice
public class ApiExceptionHandler {

  public ResponseEntity<?> tratarEntidadeNaoEcontrada(EntityNotFoundException e) {
    Problema problema = new Problema.Builder()
        .dataHora(LocalDateTime.now())
        .mensagem(e.toString()).build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
  }

}
