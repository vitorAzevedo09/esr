package com.algaworks.algafoodapi.domain.exception;

/**
 * StateNotFoundException
 */
public class StateNotFoundException extends NotFoundException{

    public StateNotFoundException(String message){
        super(message);
    }

    public StateNotFoundException(Long id){
        super(String.format("there is no kitchen with id equals to %d", id));
    }
    
}
