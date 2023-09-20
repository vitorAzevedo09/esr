package com.algaworks.algafoodapi.domain.exception;

/**
 * UserNotFoundException
 */
public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(Long id){
        super(String.format("there is no user with id equals to %d", id));
    }
}
