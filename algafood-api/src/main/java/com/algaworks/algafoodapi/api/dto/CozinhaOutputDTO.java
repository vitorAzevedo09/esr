package com.algaworks.algafoodapi.api.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * CozinhaOutputDTO
 */
@Getter
@Setter
public class CozinhaOutputDTO {

    @NonNull
    private Long id;

    @NonNull
    private String nome;
    
}
