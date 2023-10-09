package com.algaworks.algafoodapi.domain.exception;

/**
 * PaymentMethodNotFoundException
 */
public class OrderItemNotFoundException extends NotFoundException {
  public OrderItemNotFoundException(String message) {
    super(message);
  }

  public OrderItemNotFoundException(Long id) {
    super(String.format("There is no payment method with id equals to %d", id));
  }
}
