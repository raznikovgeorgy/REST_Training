package com.syncretis.rest_training.validation.personDtoValidation;

import com.syncretis.rest_training.dto.PersonDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class YearBirthdayValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PersonDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonDto dto = (PersonDto) target;
        if(dto.getBirthday().getYear() < 1900) {
            errors.rejectValue("birthday", "value.negative");
        }
    }
}
