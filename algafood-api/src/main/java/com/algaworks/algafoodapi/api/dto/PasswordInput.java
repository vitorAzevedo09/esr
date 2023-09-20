package com.algaworks.algafoodapi.api.dto;

import javax.validation.constraints.NotBlank;

/**
 * PasswordInput
 */
public record PasswordInput(
    @NotBlank String actualPassword,
    @NotBlank String newPassword) {
}
