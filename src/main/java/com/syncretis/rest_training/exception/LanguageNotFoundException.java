package com.syncretis.rest_training.exception;

public class LanguageNotFoundException extends RuntimeException {
    public LanguageNotFoundException(Long id) {
        super("Could not find language with id: " + id);
    }
}