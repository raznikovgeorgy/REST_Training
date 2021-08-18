package com.syncretis.rest_training.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String name) {
        super("Could not find user with name: " + name);
    }
}