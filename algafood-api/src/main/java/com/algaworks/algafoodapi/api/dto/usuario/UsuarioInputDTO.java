package com.algaworks.algafoodapi.api.dto.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * UsuarioInputDTO
 */
public record UsuarioInputDTO(@NotBlank String nome,@NotBlank @Email String email) {
}
