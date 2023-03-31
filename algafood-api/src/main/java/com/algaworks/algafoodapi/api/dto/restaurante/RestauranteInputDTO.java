package com.algaworks.algafoodapi.api.dto.restaurante;

import java.math.BigDecimal;

import com.algaworks.algafoodapi.api.dto.cozinha.CozinhaInputIdDTO;

/**
 * RestauranteInputDTO
 */
public record RestauranteInputDTO(
    String nome,
    BigDecimal taxa_frete,
    CozinhaInputIdDTO cozinha) {}
