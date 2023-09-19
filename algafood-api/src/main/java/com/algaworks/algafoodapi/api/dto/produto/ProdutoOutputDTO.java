package com.algaworks.algafoodapi.api.dto.produto;

import java.math.BigDecimal;

/**
 * ProductOutputDTO description.
 *
 * @param id          Product identifier; appears in "Record Components".
 * @param name        Product name appears; in "Record Components".
 * @param description Product description appears; in "Record Components".
 * @param price       Product price; appears in "Record Components".
 * @param ativo       Product ativo; appears in "Record Components".
 */
public record ProdutoOutputDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean ativo) {
}
