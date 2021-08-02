package com.syncretis.rest_training.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class PersonDto {

    private Long id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private DepartmentDto department;
    private DocumentDto document;
    private LanguageDto languages;
}