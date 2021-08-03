package com.syncretis.rest_training.advice;

import com.syncretis.rest_training.exception.person.YearOfBirthdayInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class YearOdBirthdayInvalidAdvice {
    @ResponseBody
    @ExceptionHandler(YearOfBirthdayInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidYearBirthday(YearOfBirthdayInvalidException e) {
        return e.getMessage();
    }
}
