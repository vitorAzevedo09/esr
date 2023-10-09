package com.algaworks.algafoodapi.api.dto;

import java.math.BigDecimal;

/**
 * OrderItemOutput
 */
public record OrderItemOutput(
    Long id,
    BigDecimal totalPrice,
    Integer quantity,
    String observation) {
}
