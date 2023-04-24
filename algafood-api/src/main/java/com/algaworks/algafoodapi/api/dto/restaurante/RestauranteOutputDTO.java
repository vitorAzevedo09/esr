package com.algaworks.algafoodapi.api.dto.restaurante;

import java.math.BigDecimal;

import com.algaworks.algafoodapi.api.dto.cozinha.CozinhaOutputDTO;

/**
 * RestauranteOutputDTO
 */
public record RestauranteOutputDTO(
                Long id,
                String nome,
                BigDecimal taxaFrete,
                CozinhaOutputDTO cozinha) {
}
