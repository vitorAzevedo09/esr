package com.algaworks.algafoodapi.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * OrderItemInput
 */
public record OrderItemInput(
    @NotNull Long product_id,

    @NotNull @PositiveOrZero Integer quantity,

    String observation) {
}
