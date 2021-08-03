package com.syncretis.rest_training.validation.personDtoValidation;

import com.syncretis.rest_training.dto.PersonDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.DataBinder;

@Component
@AllArgsConstructor
public class PersonValidator {
    private YearBirthdayValidator yearBirthdayValidator;

    public boolean validate(PersonDto dto) {
        DataBinder dataBinder = new DataBinder(dto);
        dataBinder.addValidators(yearBirthdayValidator);
        dataBinder.validate();
        return !dataBinder.getBindingResult().hasErrors();
    }
}