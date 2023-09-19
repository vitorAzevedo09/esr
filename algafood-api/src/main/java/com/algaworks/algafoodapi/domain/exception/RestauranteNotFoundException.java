package com.algaworks.algafoodapi.domain.exception;

/**
 * RestauranteNotFoundException
 */
public class RestauranteNotFoundException extends NotFoundException {
    private static final long serialVersionUID = 1L;

    public RestauranteNotFoundException(String mensagem) {
        super(mensagem);
    }

    public RestauranteNotFoundException(Long id) {
        this(String.format("Não exist um cadastro de restaurante com o id %d", id));
    }

}
