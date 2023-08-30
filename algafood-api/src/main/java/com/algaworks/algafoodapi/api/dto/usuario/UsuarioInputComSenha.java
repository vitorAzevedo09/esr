package com.algaworks.algafoodapi.api.dto.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * UsuarioInputComSenha
 */
public record UsuarioInputComSenha(@NotBlank String nome,@NotBlank @Email String email, @NotBlank String senha) {
}
