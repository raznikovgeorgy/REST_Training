package com.syncretis.rest_training.exception;

public class PersonNotValidDataException extends RuntimeException {

    public PersonNotValidDataException() {
        super("You sen not valid data for saving, please correct data and try again");
    }
}