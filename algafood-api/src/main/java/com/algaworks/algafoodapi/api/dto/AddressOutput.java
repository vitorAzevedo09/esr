
package com.algaworks.algafoodapi.api.dto;

/**
 * EnderecoOutputDTO
 */
public record AddressOutput(
        String postal_code,
        String street,
        String number,
        String complement,
        String neighborhood) {
}
