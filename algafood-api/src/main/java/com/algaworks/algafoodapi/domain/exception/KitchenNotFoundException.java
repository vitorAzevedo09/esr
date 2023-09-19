package com.algaworks.algafoodapi.domain.exception;

/**
 * KitchenNotFoundException
 */
public class KitchenNotFoundException extends NotFoundException{
    public KitchenNotFoundException(String message){
        super(message);
    }

    public KitchenNotFoundException(Long id){
        super(String.format("There is no kitchen with id equals to %d", id));
    }
}
