package com.algaworks.algafoodapi.api.exceptionhandler;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algafoodapi.domain.exception.RestauranteNotFoundException;

/**
 * ApiExceptionHandler
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ Exception.class })
  public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

    Problema problema = Problema.Builder.newInstance()
        .title(ex.getMessage())
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();

    return handleExceptionInternal(ex, problema, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  @ExceptionHandler({ DataIntegrityViolationException.class })
  public ResponseEntity<Object> handleDataIntegrityViolationException(Exception ex, WebRequest request) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    Problema problema = Problema.Builder.newInstance()
        .title("Data integrity violation")
        .status(status.value())
        .detail(ex.getMessage())
        .build();

    return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);

  }

    @ExceptionHandler({ RestauranteNotFoundException.class })
    public ResponseEntity<Object> handleNoSuchElementException(Exception ex, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;

        Problema problema = Problema.Builder.newInstance()
        .title("Elemento não encontrado")
        .status(status.value())
        .detail(ex.getMessage())
        .type("Dados inválidos")
        .build();
        
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    Problema problema = Problema.Builder.newInstance()
        .title("DADOS REQUISITANTES INVÀLIDOS")
        .detail(status.getReasonPhrase())
        .status(status.value()).build();

    return new ResponseEntity<Object>(problema, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleConstraintViolationException(
      ConstraintViolationException constraintViolationException) {

    HttpStatus status = HttpStatus.BAD_REQUEST;

    Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();

    String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente";

    List<Problema.Field> problemaFields = violations.stream().map(violation -> Problema.Field.Builder.newInstance()
        .name(violation.getPropertyPath().toString())
        .userMessage(violation.getMessage())
        .build()).toList();

    Problema problema = Problema.Builder.newInstance()
        .title("Error no request")
        .status(status.value())
        .type("DADOS INVÀLIDOS")
        .detail(detail)
        .fields(problemaFields)
        .build();

    return new ResponseEntity<>(problema, status);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente";

    BindingResult bindingResults = ex.getBindingResult();

    List<Problema.Field> problemaFields = bindingResults.getFieldErrors().stream()
        .map(fieldErr -> Problema.Field.Builder.newInstance()
            .name(fieldErr.getField())
            .userMessage(fieldErr.getDefaultMessage()).build())
        .toList();

    Problema problema = Problema.Builder.newInstance()
        .status(status.value())
        .type("DADOS INVÀLIDOS")
        .detail(detail)
        .fields(problemaFields)
        .build();

    return new ResponseEntity<Object>(problema, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

}
