package com.syncretis.rest_training.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syncretis.rest_training.model.OpenWeather.OpenWeatherObject;
import com.syncretis.rest_training.service.user.UserService;
import lombok.AllArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;

@Service
@AllArgsConstructor
public class WeatherService {
    private final static Double KELVIN = -273.15;
    private final static String APIKEY = "d335f3ec955d7a54161a530b06f676fb";

    private UserService userService;

    public String getWeather(String token) {
        String json = getJsonFromApi(token);
        OpenWeatherObject object = mapJsonToOpenWeatherObject(json);
        assert object != null;
        return buildWeatherDisplayedData(object);
    }

    private String buildWeatherDisplayedData(OpenWeatherObject openWeather) {
        DecimalFormat df = new DecimalFormat("#.#");
        Double temp = openWeather.getMain().getTemp() + KELVIN;
        return "Weather outside " +
                openWeather.getName() +
                " is: " +
                openWeather.getWeather().get(0).getDescription() +
                ", temperature is: " +
                df.format(temp) +
                " degrees Celsius, windspeed is: " +
                df.format(openWeather.getWind().getSpeed()) +
                " meters per second.";
    }

    private HttpUrl buildUrl(String city) {
        return new HttpUrl.Builder()
                .scheme("https")
                .host("api.openweathermap.org")
                .addPathSegment("data")
                .addPathSegment("2.5")
                .addPathSegment("weather")
                .addQueryParameter("q", city)
                .addQueryParameter("appid", APIKEY)
                .build();
    }

    private String getJsonFromApi(String token) {
        String city = userService.getUserFromToken(token).getCity();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(buildUrl(city)).build();
        try {
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private OpenWeatherObject mapJsonToOpenWeatherObject(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, OpenWeatherObject.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}