package com.syncretis.rest_training.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class LanguageDto {
    private Long id;
    @NotBlank(message = "name is mandatory")
    @Pattern(regexp = "^[A-Za-z]*$")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LanguageDto that = (LanguageDto) o;
        return id.equals(that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result *= 37 + Objects.hashCode(id);
        result *= 37 + Objects.hashCode(name);
        return result;
    }
}