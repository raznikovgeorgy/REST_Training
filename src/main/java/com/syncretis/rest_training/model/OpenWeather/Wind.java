package com.syncretis.rest_training.model.OpenWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind {
    private Float speed;
    private Float deg;
    private Float gust;
}