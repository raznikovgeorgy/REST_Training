package com.syncretis.rest_training.model.OpenWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rain {
    private Float oneHour;

    public Rain(Float oneHour) {
        this.oneHour = oneHour;
    }

    @JsonProperty("1h")
    public Float getOneHour() {
        return oneHour;
    }

    @JsonProperty("1h")
    public void setOneHour(Float oneHour) {
        this.oneHour = oneHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rain rain = (Rain) o;
        return Objects.equals(oneHour, rain.oneHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oneHour);
    }

    @Override
    public String toString() {
        return "Rain{" +
                "1h=" + oneHour +
                '}';
    }
}