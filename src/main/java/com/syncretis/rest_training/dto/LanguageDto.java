package com.syncretis.rest_training.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class LanguageDto {

    private Long id;
    @NotBlank(message = "name is mandatory")
    @Pattern(regexp = "^[A-Za-z]*$")
    private String name;
}