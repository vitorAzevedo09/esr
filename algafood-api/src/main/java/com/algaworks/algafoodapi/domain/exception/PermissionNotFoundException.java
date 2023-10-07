package com.algaworks.algafoodapi.domain.exception;

/**
 * PermissionNotFoundException
 */
public class PermissionNotFoundException extends NotFoundException {

  public PermissionNotFoundException(String message) {
    super(message);
  }

  public PermissionNotFoundException(Long id) {
    super(String.format("There is no permission with id equals to %d", id));
  }

}
