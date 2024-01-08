package com.example.service;

import com.example.dto.AccuWeatherResponse;
import com.example.dto.OpenWeatherMapResponse;
import com.example.dto.WeatherInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class OpenWeatherMapDataExtractor {

    public static double extractLatitude(String openGeocoderInfo) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(openGeocoderInfo);

            // Assuming a simple structure, modify this based on your actual response
            return rootNode.path("lat").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, log, or return a default value
            return 0.0;
        }
    }

    public static double extractLongitude(String openGeocoderInfo) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(openGeocoderInfo);

            // Assuming a simple structure, modify this based on your actual response
            return rootNode.path("lon").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, log, or return a default value
            return 0.0;
        }
    }

    public static OpenWeatherMapResponse extractOpenWeatherMapData(String openWeatherMapDetails) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(openWeatherMapDetails);

            // Assuming a simple structure, modify this based on your actual response
            double temperature = rootNode.path("main").path("temp").asDouble();
            double feelsLike = rootNode.path("main").path("feels_like").asDouble();
            int pressure = rootNode.path("main").path("pressure").asInt();
            int humidity = rootNode.path("main").path("humidity").asInt();
            int visibility = rootNode.path("visibility").asInt();
            double windSpeed = rootNode.path("wind").path("speed").asDouble();
            int windDegree = rootNode.path("wind").path("deg").asInt();
            double windGust = rootNode.path("wind").path("gust").asDouble();
            long sunrise = rootNode.path("sys").path("sunrise").asLong();
            long sunset = rootNode.path("sys").path("sunset").asLong();

            return new OpenWeatherMapResponse(temperature, feelsLike, pressure, humidity, visibility,
                    windSpeed, windDegree, windGust, sunrise, sunset);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, log, or return a default OpenWeatherMapData
            return null;
        }
    }

    private WeatherInfo transformToWeatherInfo(AccuWeatherResponse accuWeatherData, OpenWeatherMapResponse openWeatherMapData) {
        // Implement logic to transform and combine the data into the final WeatherInfo object
        // ...
        return new WeatherInfo(); // Replace with the actual WeatherInfo object
    }
}