package com.syncretis.rest_training.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@ToString
public class PersonDto {
    private Long id;
    @NotBlank(message = "name is mandatory")
    @Size(max = 100)
    @Pattern(regexp = "^[A-Za-z]*$")
    private String name;
    @Size(max = 100)
    @Pattern(regexp = "^[A-Za-z]*$")
    @NotBlank(message = "surname is mandatory")
    private String surname;
    @PastOrPresent
    private LocalDate birthday;
    @DecimalMin("1")
    private Long departmentId;
    @NotBlank(message = "name is mandatory")
    private String documentId;
    @NotEmpty
    private List<Long> languageIds;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDto personDto = (PersonDto) o;
        return id.equals(personDto.id) && name.equals(personDto.name) &&
                surname.equals(personDto.surname) && birthday.equals(personDto.birthday) &&
                departmentId.equals(personDto.departmentId) && documentId.equals(personDto.documentId) &&
                languageIds.equals(personDto.languageIds);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result *= 37 + Objects.hashCode(id);
        result *= 37 + Objects.hashCode(name);
        result *= 37 + Objects.hashCode(surname);
        result *= 37 + Objects.hashCode(birthday);
        result *= 37 + Objects.hashCode(departmentId);
        result *= 37 + Objects.hashCode(documentId);
        result *= 37 + Objects.hashCode(languageIds);
        return result;
    }
}