package com.syncretis.rest_training.validation.personDtoValidation;

import com.syncretis.rest_training.dto.PersonDto;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class YearBirthdayValidatorTest {
    private final YearBirthdayValidator validator = new YearBirthdayValidator();
    private final static Long id = 1L;
    private PersonDto dto;
    private BindException errors;

    @Test
    void shouldCheckForSupportingDtoClass() {
        //WHEN
        boolean actual = validator.supports(PersonDto.class);
        //THEN
        assertThat(actual).isEqualTo(true);
    }

    @Test
    void shouldFailValidation() {
        initializeData();
        //WHEN
        ValidationUtils.invokeValidator(validator, dto, errors);
        //THEN
        assertThat(errors.hasErrors()).isEqualTo(true);
    }

    @Test
    void shouldPassValidation() {
        initializeData();
        //GIVEN
        dto.setBirthday(LocalDate.of(1997, 3, 12));
        //WHEN
        ValidationUtils.invokeValidator(validator, dto, errors);
        //THEN
        assertThat(errors.hasErrors()).isEqualTo(false);
    }

    private void initializeData() {
        dto = new PersonDto(id, "Georgy", "Raznikov",
                LocalDate.of(1888, 3, 12), id, "agh6jh15a1ggj9hs68n1sf6", List.of(id));
        errors = new BindException(dto, "dto");
    }
}