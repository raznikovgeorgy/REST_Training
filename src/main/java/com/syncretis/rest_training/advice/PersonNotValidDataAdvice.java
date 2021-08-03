package com.syncretis.rest_training.advice;

import com.syncretis.rest_training.exception.PersonNotValidDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PersonNotValidDataAdvice {
    @ResponseBody
    @ExceptionHandler(PersonNotValidDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String personNotValidDataAdvice(PersonNotValidDataException e) {
        return e.getMessage();
    }
}