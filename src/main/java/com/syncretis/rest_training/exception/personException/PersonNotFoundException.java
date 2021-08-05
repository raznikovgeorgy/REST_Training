package com.syncretis.rest_training.exception.personException;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(Long id) {
        super("Could not find person with id: " + id);
    }
}