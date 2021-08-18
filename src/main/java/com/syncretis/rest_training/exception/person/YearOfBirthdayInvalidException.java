package com.syncretis.rest_training.exception.person;

public class YearOfBirthdayInvalidException extends RuntimeException {
    public YearOfBirthdayInvalidException() {
        super("Year must be more 1900");
    }
}
