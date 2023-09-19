package com.algaworks.algafoodapi.domain.exception;

/**
 * CityNotFoundException
 */
public class CityNotFoundException extends NotFoundException {

    public CityNotFoundException(String message){
        super(message);
    }

    public CityNotFoundException(Long id){
        super(String.format("There is no city with id equals to %d", id));
    }
    
}
