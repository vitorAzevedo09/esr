package com.algaworks.algafoodapi.domain.exception;

/**
 * GroupNotFoundException
 */
public class OrderNotFoundException extends NotFoundException {
  public OrderNotFoundException(String message) {
    super(message);
  }

  public OrderNotFoundException(Long id) {
    super(String.format("there is no order with id equals to %d", id));
  }
}
