package com.algaworks.algafoodapi.api.schemas.input;

import lombok.Data;
import lombok.NonNull;

/**
 * Cozinha
 */
@Data
public class CozinhaInput {

    @NonNull
    private Long id;

    @NonNull
    private String nome;
}
