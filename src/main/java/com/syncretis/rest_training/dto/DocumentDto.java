package com.syncretis.rest_training.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class DocumentDto {
    private String id;
    @NotNull(message = "expireDate is mandatory")
    private LocalDate expireDate;
}
