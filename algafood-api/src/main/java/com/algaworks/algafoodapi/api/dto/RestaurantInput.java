package com.algaworks.algafoodapi.api.dto;

import java.math.BigDecimal;

/**
 * RestaurantInputDTO
 */
public record RestaurantInput(
        String name,
        BigDecimal shipping_fee,
        CuisineOnlyIdOutput cuisine) {
}
