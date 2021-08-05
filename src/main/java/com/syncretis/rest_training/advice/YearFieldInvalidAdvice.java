package com.syncretis.rest_training.advice;

import com.syncretis.rest_training.exception.personException.YearBirthdayInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class YearFieldInvalidAdvice {
    @ResponseBody
    @ExceptionHandler(YearBirthdayInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidYearBirthday(YearBirthdayInvalidException e) {
        return e.getMessage();
    }
}
