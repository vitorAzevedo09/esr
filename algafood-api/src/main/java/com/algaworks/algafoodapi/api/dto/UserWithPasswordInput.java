package com.algaworks.algafoodapi.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * UserWithPasswordInput
 */
public record UserWithPasswordInput(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String password) {
}
