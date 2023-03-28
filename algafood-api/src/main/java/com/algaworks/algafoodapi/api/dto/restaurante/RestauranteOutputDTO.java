package com.algaworks.algafoodapi.api.dto;

import java.math.BigDecimal;
import java.util.List;

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
