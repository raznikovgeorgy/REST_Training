package com.syncretis.rest_training.validation.personDtoValidation;

import com.syncretis.rest_training.dto.PersonDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonValidatorTest {
    @Spy
    private YearBirthdayValidator yearBirthdayValidator;
    @InjectMocks
    private PersonValidator validator;

    @Test
    void validate() {
        //GIVEN
        Long id = 1L;
        String name = "Georgy";
        String surname = "Raznikov";
        String docId = "3as7fasd8fsda6fds6a8f53sa7";
        List<Long> idList = List.of(id);
        LocalDate birthday = LocalDate.of(1997, 3, 12);
        PersonDto dto = new PersonDto(id, name, surname, birthday, id, docId, idList);
        //WHEN
        boolean actual = validator.validate(dto);
        //THEN
        verify(yearBirthdayValidator).validate(any(), any());
        assertThat(actual).isEqualTo(true);
    }
}