package com.algaworks.algafoodapi.api.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * ApiExceptionHandler
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ Exception.class })
  public ResponseEntity<Object> handleAll(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

    Problema problema = Problema.Builder.newInstance()
        .title(status.getReasonPhrase())
        .status(status.value()).build();

    return new ResponseEntity<Object>(
        problema, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    Problema problema = Problema.Builder.newInstance()
        .title("Entidade não encontrada")
        .detail(status.getReasonPhrase())
        .status(status.value()).build();

    return new ResponseEntity<Object>(problema, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

}
