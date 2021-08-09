package com.syncretis.rest_training.exception.personException;

public class YearBirthdayInvalidException extends RuntimeException{

    public YearBirthdayInvalidException() {
        super("Year must be more 1900");
    }
}
