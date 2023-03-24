package com.algaworks.algafoodapi.api.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * CozinhaInputDTO
 */
@Getter
@Setter
public class CozinhaInputDTO {

    @NonNull
    private Long id;

    @NonNull
    private String nome;
}
