package com.algaworks.algafoodapi.api.dto;

import javax.validation.constraints.NotNull;

/**
 * RestaurantIdInput
 */
public record RestaurantIdInput(@NotNull Long id) {
}
