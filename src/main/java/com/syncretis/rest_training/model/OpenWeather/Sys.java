package com.syncretis.rest_training.model.OpenWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sys {
    private String country;
    private int sunrise;
    private int sunset;
}