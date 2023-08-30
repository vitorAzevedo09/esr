package com.algaworks.algafoodapi.api.dto.usuario;

import javax.validation.constraints.NotBlank;

/**
 * SenhaInput
 */
public record SenhaInput(@NotBlank String senhaAtual,@NotBlank String novaSenha) {
}
