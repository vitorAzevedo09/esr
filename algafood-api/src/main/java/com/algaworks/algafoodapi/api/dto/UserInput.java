
package com.algaworks.algafoodapi.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * useInput
 */
public record UserInput(
        @NotBlank String name,
        @NotBlank @Email String email) {
}
