package com.algaworks.algafoodapi.domain.exception;

/**
 * ProductNotFoundException
 */
public class ProductNotFoundException extends NotFoundException {

  public ProductNotFoundException(String message) {
    super(message);
  }

  public ProductNotFoundException(Long restaurantID, Long productID) {
    super(String.format("There is no restaurant with id equals to %d with id products' equals to %d", restaurantID,
        productID));
  }

}
