package com.example.controller;

import com.example.dto.WeatherAggregationResponse;
import com.example.dto.WeatherInfo;
import com.example.service.AccuWeatherService;
import com.example.service.WeatherAggregatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherAggregatorService weatherAggregatorService;
    @Autowired
    private AccuWeatherService accuWeatherService;


    @Autowired
    public WeatherController(WeatherAggregatorService weatherAggregatorService) {
        this.weatherAggregatorService = weatherAggregatorService;
    }

    @GetMapping
    public ResponseEntity<WeatherAggregationResponse> getWeather(
            @RequestParam String city,
            @RequestParam String zip,
            @RequestParam String country) {
        WeatherAggregationResponse response = weatherAggregatorService.getWeatherAggregation(city, zip, country);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/locationKey")
    public String getLocationKey(@RequestParam String city) {
        try {
            // Call the AccuWeatherService to get the location key
            String locationKey = accuWeatherService.getLocationKey(city);

            // Log the result
            System.out.println("Location Key for " + city + ": " + locationKey);

            return locationKey;
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            e.printStackTrace(); // Log the exception for debugging
            return "Error getting location key";
        }
    }
    @GetMapping("/weatherInfo")
    public ResponseEntity<WeatherInfo> getWeatherInfo(@RequestParam String city, @RequestParam String zip) {
        try {
            // Fetch weather information from AccuWeather and OpenWeatherMap
            WeatherInfo weatherInfo = weatherAggregatorService.getAggregatedWeather(city, zip);

            // Log the result or perform additional operations if needed
            System.out.println("Weather Information for " + city + ": " + weatherInfo);

            // Return the weather information in the response
            return ResponseEntity.ok(weatherInfo);
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
