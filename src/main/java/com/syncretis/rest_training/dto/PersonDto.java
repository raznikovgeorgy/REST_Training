package com.syncretis.rest_training.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
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
    private Long department_id;
    @NotBlank(message = "name is mandatory")
    private String document_id;
    @NotEmpty
    private List<Long> languageIds;
}