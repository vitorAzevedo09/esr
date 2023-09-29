package com.algaworks.algafoodapi.api.dto;

import java.math.BigDecimal;

public record ProductOutput(
                Long id,
                String name,
                String description,
                BigDecimal price,
                Boolean active) {
}
