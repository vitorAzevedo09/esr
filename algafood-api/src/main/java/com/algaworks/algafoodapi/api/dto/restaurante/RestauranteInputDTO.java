package com.algaworks.algafoodapi.api.dto.restaurante;

import java.math.BigDecimal;

import com.algaworks.algafoodapi.api.dto.cozinha.CozinhaOutputDTO;

/**
 * RestauranteInputDTO
 */
public record RestauranteInputDTO(
    String nome,
    BigDecimal taxaFrete,
    CozinhaOutputDTO cozinha) {}
