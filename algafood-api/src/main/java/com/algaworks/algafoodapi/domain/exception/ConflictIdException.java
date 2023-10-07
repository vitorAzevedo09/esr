package com.algaworks.algafoodapi.domain.exception;

/**
 * NotFoundException
 */
public abstract class ConflictIdException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ConflictIdException(String message) {
    super(message);
  }

}
