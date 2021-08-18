package com.syncretis.rest_training.controller;

import com.syncretis.rest_training.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/weather")
public class WeatherController {
    private WeatherService service;

    @GetMapping("")
    @ResponseBody
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<String> getWeather(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(service.getWeather(token), HttpStatus.OK);
    }
}