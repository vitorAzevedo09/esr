package com.algaworks.algafoodapi.domain.exception;

/**
 * CuisineNotFoundException
 */
public class CuisineNotFoundException extends NotFoundException{
    public CuisineNotFoundException(String message){
        super(message);
    }

    public CuisineNotFoundException(Long id){
        super(String.format("There is no kitchen with id equals to %d", id));
    }
}
