package com.syncretis.rest_training.exception.personException;

public class PersonNotValidDataException extends RuntimeException {

    public PersonNotValidDataException() {
        super("You sen not valid data for saving, please correct data and try again");
    }
}