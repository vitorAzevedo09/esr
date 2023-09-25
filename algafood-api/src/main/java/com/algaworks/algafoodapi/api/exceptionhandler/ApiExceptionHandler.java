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

import com.algaworks.algafoodapi.domain.exception.NotFoundException;

/**
 * ApiExceptionHandler
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

        Problem problem = Problem.Builder.newInstance()
                .title(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        Problem problem = Problem.Builder.newInstance()
                .title("Data integrity violation")
                .status(status.value())
                .detail(ex.getMessage())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

    }

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<Object> handleNoSuchElementException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        List<Problem.Field> fields = List.of(Problem.Field.Builder
                .newInstance()
                .name("id")
                .userMessage("Id not found in the system")
                .build());

        Problem problem = Problem.Builder.newInstance()
                .title("Element not found")
                .status(status.value())
                .detail(ex.getMessage())
                .type("Invalid data")
                .fields(fields)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        Problem problem = Problem.Builder.newInstance()
                .title("DADOS REQUISITANTES INVÀLIDOS")
                .detail(status.getReasonPhrase())
                .status(status.value()).build();

        return new ResponseEntity<Object>(problem, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException constraintViolationException) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();

        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente";

        List<Problem.Field> problemFields = violations.stream().map(violation -> Problem.Field.Builder.newInstance()
                .name(violation.getPropertyPath().toString())
                .userMessage(violation.getMessage())
                .build()).toList();

        Problem problem = Problem.Builder.newInstance()
                .title("Error no request")
                .status(status.value())
                .type("DADOS INVÀLIDOS")
                .detail(detail)
                .fields(problemFields)
                .build();

        return new ResponseEntity<>(problem, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente";

        BindingResult bindingResults = ex.getBindingResult();

        List<Problem.Field> problemFields = bindingResults.getFieldErrors().stream()
                .map(fieldErr -> Problem.Field.Builder.newInstance()
                        .name(fieldErr.getField())
                        .userMessage(fieldErr.getDefaultMessage()).build())
                .toList();

        Problem problem = Problem.Builder.newInstance()
                .status(status.value())
                .type("DADOS INVÀLIDOS")
                .detail(detail)
                .fields(problemFields)
                .build();

        return new ResponseEntity<Object>(problem, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
