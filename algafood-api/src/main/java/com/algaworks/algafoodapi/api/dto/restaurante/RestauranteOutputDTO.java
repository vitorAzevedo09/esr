package com.algaworks.algafoodapi.api.dto.restaurante;

import java.math.BigDecimal;

/**
 * RestauranteOutputDTO
 */
public record RestauranteOutputDTO(
                                Long id,
                                String nome,
                                BigDecimal taxaFrete,
                                boolean ativo) {
}
