package com.example.service;

import com.example.dto.AccuWeatherResponse;
import com.example.dto.AccuWeatherLocationResponse;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

@Service
public class AccuWeatherService {
    @Value("${accuweather.api.key}")
    private String accuWeatherApiKey;

    private final RestTemplate restTemplate;

    @Autowired
    public AccuWeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getLocationKey(String city) {
        // Placeholder logic to fetch location key from AccuWeather API
        String accuWeatherApiUrl = "https://dataservice.accuweather.com/locations/v1/search?q={city}&apikey={apiKey}";

        // Make a REST call to AccuWeather API
        AccuWeatherLocationResponse[] locationResponses = restTemplate.getForObject(
                accuWeatherApiUrl,
                AccuWeatherLocationResponse[].class,
                city,
                accuWeatherApiKey
        );

        // Extract and return the location key (assuming the API returns an array)
        if (locationResponses != null && locationResponses.length > 0) {
            return locationResponses[0].getKey();
        } else {
            throw new RuntimeException("Unable to fetch location key from AccuWeather API");
        }
    }

    public AccuWeatherResponse getWeatherInfo(String locationKey) {
        // Placeholder logic to fetch weather info from AccuWeather API
        String accuWeatherApiUrl = "https://dataservice.accuweather.com/currentconditions/v1/{locationKey}?apikey={apiKey}";

        // Make a REST call to AccuWeather API
        AccuWeatherResponse[] weatherResponses = restTemplate.getForObject(
                accuWeatherApiUrl,
                AccuWeatherResponse[].class,
                locationKey,
                accuWeatherApiKey
        );

        // Extract and return the weather info (assuming the API returns an array)
        if (weatherResponses != null && weatherResponses.length > 0) {
            return weatherResponses[0];
        } else {
            throw new RuntimeException("Unable to fetch weather info from AccuWeather API");
        }
    }

    public String getWeatherInformation(String accuLocationKey) {
        try {
            String accuWeatherApiUrl = "https://dataservice.accuweather.com/currentconditions/v1/{locationKey}?apikey={apiKey}";

            // Make a REST call to the AccuWeather Current Conditions API
            return restTemplate.getForObject(
                    accuWeatherApiUrl,
                    String.class,
                    accuLocationKey,
                    accuWeatherApiKey
            );
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return null; // Handle the exception and return an appropriate response
        }
    }
}
