package com.algaworks.algafoodapi.api.dto;

import java.math.BigDecimal;

public record ProductOutput(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean ativo) {
}
