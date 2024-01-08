package com.example.service;

import com.example.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.CompletableFuture;

import static com.example.service.OpenWeatherMapDataExtractor.*;

@Service
public class WeatherAggregatorService {
    private final AccuWeatherService accuWeatherService;
    private final OpenWeatherMapService openWeatherMapService;

    @Autowired
    public WeatherAggregatorService(
            AccuWeatherService accuWeatherService,
            OpenWeatherMapService openWeatherMapService) {
        this.accuWeatherService = accuWeatherService;
        this.openWeatherMapService = openWeatherMapService;
    }

    public WeatherAggregationResponse getWeatherAggregation(String city, String zipCode, String countryCode) {
        // Placeholder logic to fetch data from AccuWeather and OpenWeatherMap services
        String accuWeatherLocationKey = accuWeatherService.getLocationKey(city);
        AccuWeatherResponse accuWeatherResponse = accuWeatherService.getWeatherInfo(accuWeatherLocationKey);

        OpenWeatherMapGeocodeResponse openWeatherMapGeocodeResponse = openWeatherMapService.getGeocodeInfo(zipCode, countryCode);
        OpenWeatherMapResponse openWeatherMapResponse = openWeatherMapService.getWeatherInfo(openWeatherMapGeocodeResponse.getLat(), openWeatherMapGeocodeResponse.getLon());

        // Placeholder logic to aggregate data into WeatherAggregationResponse
        WeatherAggregationResponse weatherAggregationResponse = new WeatherAggregationResponse();
        // Set properties based on the fetched data from AccuWeather and OpenWeatherMap
        // For example:
        weatherAggregationResponse.setWeatherText(accuWeatherResponse.getWeatherText());
        weatherAggregationResponse.setHasPrecipitation(accuWeatherResponse.isHasPrecipitation());
        // ... set other properties ...

        return weatherAggregationResponse;
    }

    public WeatherInfo getAggregatedWeather(String city, String zip) {
        try {
            // Fetch location key from AccuWeather asynchronously
            CompletableFuture<String> accuWeatherLocationKeyFuture = CompletableFuture
                    .supplyAsync(() -> accuWeatherService.getLocationKey(city));

            // Fetch geocoder information from OpenWeatherMap asynchronously
            CompletableFuture<String> openWeatherMapGeocoderInfoFuture = CompletableFuture
                    .supplyAsync(() -> openWeatherMapService.getGeocoderInfo(zip, "IN"));

            // Combine results when both API calls are complete
            CompletableFuture<WeatherInfo> combinedResult = accuWeatherLocationKeyFuture
                    .thenCombine(openWeatherMapGeocoderInfoFuture, (accuLocationKey, openGeocoderInfo) -> {
                        // Fetch weather information from AccuWeather using the obtained location key
                        String accuWeatherInfo = accuWeatherService.getWeatherInformation(accuLocationKey);

                        // Extract required data from AccuWeather response (Modify based on the actual response structure)
                        String accuWeatherData = extractAccuWeatherData(accuWeatherInfo);

                        // Extract latitude and longitude from OpenWeatherMap geocoder response (Modify based on the actual response structure)
                        double latitude = extractLatitude(openGeocoderInfo);
                        double longitude = extractLongitude(openGeocoderInfo);

                        // Fetch weather details from OpenWeatherMap
                        String openWeatherMapDetails = openWeatherMapService.getWeatherDetails(latitude, longitude);

                        // Extract required data from OpenWeatherMap response (Modify based on the actual response structure)
                        String openWeatherMapData = extractOpenWeatherMapData(openWeatherMapDetails);

                        // Combine and transform the data into the final WeatherInfo object
                        return transformToWeatherInfo(accuWeatherData, openWeatherMapData);
                    });

            // Wait for the combined result to be available
            return combinedResult.get();
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            e.printStackTrace(); // Log the exception for debugging
            return new WeatherInfo(); // Return a default WeatherInfo in case of an error
        }
    }

    public static AccuWeatherData extractAccuWeatherData(String accuWeatherInfo) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(accuWeatherInfo);

            // Assuming a simple structure, modify this based on your actual response
            String weatherText = rootNode.path(0).path("WeatherText").asText();
            boolean hasPrecipitation = rootNode.path(0).path("HasPrecipitation").asBoolean();
            String precipitationType = rootNode.path(0).path("PrecipitationType").asText();
            boolean isDayTime = rootNode.path(0).path("IsDayTime").asBoolean();

            // Assuming a nested structure for temperature, modify this based on your actual response
            double temperatureValue = rootNode.path(0).path("Temperature").path("Metric").path("Value").asDouble();
            String temperatureUnit = rootNode.path(0).path("Temperature").path("Metric").path("Unit").asText();

            return new AccuWeatherData(weatherText, hasPrecipitation, precipitationType, isDayTime,
                    new AccuWeatherData.TemperatureInfo(temperatureValue, temperatureUnit));
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            // Handle the exception and return null or an appropriate response
            return null;
        }
    }

}
