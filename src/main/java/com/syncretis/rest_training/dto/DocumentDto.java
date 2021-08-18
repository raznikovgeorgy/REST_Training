package com.syncretis.rest_training.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class DocumentDto {
    private String id;
    @NotNull(message = "expireDate is mandatory")
    private LocalDate expireDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentDto that = (DocumentDto) o;
        return id.equals(that.id) && expireDate.equals(that.expireDate);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result *= 37 + Objects.hashCode(id);
        result *= 37 + Objects.hashCode(expireDate);
        return result;
    }
}