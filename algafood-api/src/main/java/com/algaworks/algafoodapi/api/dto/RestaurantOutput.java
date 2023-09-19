package com.algaworks.algafoodapi.api.dto;

import java.math.BigDecimal;

/**
 * RestauranteOutputDTO
 */
public record RestaurantOutput(
        Long id,
        String name,
        BigDecimal shipping_fee,
        boolean active) {
}
