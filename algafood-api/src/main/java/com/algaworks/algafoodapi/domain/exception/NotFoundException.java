package com.algaworks.algafoodapi.domain.exception;

/**
 * NotFoundException
 */
public abstract class NotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L; 

    public NotFoundException(String mensagem){
        super(mensagem);
    }
    
}
