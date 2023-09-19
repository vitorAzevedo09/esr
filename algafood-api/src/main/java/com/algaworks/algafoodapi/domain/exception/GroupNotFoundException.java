
package com.algaworks.algafoodapi.domain.exception;

/**
 * GroupNotFoundException
 */
public class GroupNotFoundException extends NotFoundException{
    public GroupNotFoundException(String message){
        super(message);
    }

    public GroupNotFoundException(Long id){
        super(String.format("there is no group with id equals to %d", id));
    }
}
