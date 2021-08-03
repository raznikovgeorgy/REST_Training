package com.syncretis.rest_training.exception.person;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(Long id) {
        super("Could not find person with id: " + id);
    }

    public PersonNotFoundException(Long id, String message) {
        super("Could not find person with id: " + id + message);
    }
}