package com.syncretis.rest_training.validation.personDtoValidation;

import com.syncretis.rest_training.dto.PersonDto;
import com.syncretis.rest_training.model.Language;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PersonValidatorTest {
    private final YearBirthdayValidator yearBirthdayValidator = new YearBirthdayValidator();
    private final PersonValidator validator = new PersonValidator(yearBirthdayValidator);

    @Test
    void validate() {
        //GIVEN
        Long id = 1L;
        Language language = new Language("RU");
        String name = "Georgy";
        String surname = "Raznikov";
        String docId = "3as7fasd8fsda6fds6a8f53sa7";
        List<Long> idList = List.of(id);
        LocalDate birthday = LocalDate.of(1997, 3, 12);
        PersonDto dto = new PersonDto(id, name, surname, birthday, id, docId, idList);
        //WHEN
        boolean actual = validator.validate(dto);
        //THEN
        assertThat(actual).isEqualTo(true);
    }
}