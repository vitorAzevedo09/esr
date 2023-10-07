package com.algaworks.algafoodapi.domain.exception;

/**
 * GroupConflictIdException
 */
public class GroupConflictIdException extends ConflictIdException {

  public GroupConflictIdException(String message) {
    super(message);
  }

  public GroupConflictIdException(Long id) {
    super(String.format("The group with id %d already exist", id));
  }

}
