package com.algaworks.algafoodapi.api.schemas.output;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

/**
 * Cozinha
 */
@Getter
@Setter
public class CozinhaOutput {
    
    @NonNull
    private Long id;

    @NonNull
    private String nome;

}
