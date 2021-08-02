package com.syncretis.rest_training.exception;

public class DocumentNotFoundException extends RuntimeException{

    public DocumentNotFoundException(String id) {
        super("Could not find document " + id);
    }
}