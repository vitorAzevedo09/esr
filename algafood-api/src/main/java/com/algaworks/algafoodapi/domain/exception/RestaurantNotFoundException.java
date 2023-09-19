package com.algaworks.algafoodapi.domain.exception;

/**
 * RestauranteNotFoundException
 */
public class RestaurantNotFoundException extends NotFoundException {
    private static final long serialVersionUID = 1L;

    public RestaurantNotFoundException(String mensagem) {
        super(mensagem);
    }

    public RestaurantNotFoundException(Long id) {
        this(String.format("Não exist um cadastro de restaurante com o id %d", id));
    }

}
