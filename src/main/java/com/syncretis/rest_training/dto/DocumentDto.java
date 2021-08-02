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
public class DocumentDto {

    private String id;
    private LocalDate expireDate;
}
