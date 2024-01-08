package com.example.service;

import com.example.dto.OpenWeatherMapGeocodeResponse;
import com.example.dto.OpenWeatherMapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherMapService {
    @Value("${openweathermap.api.key}")
    private String openWeatherMapApiKey;

    private final RestTemplate restTemplate;

    @Autowired
    public OpenWeatherMapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OpenWeatherMapGeocodeResponse getGeocodeInfo(String zipCode, String countryCode) {
        // Placeholder logic to fetch geocode info from OpenWeatherMap API
        String openWeatherMapGeocodeApiUrl = "http://api.openweathermap.org/geo/1.0/zip?zip={zipCode},{countryCode}&appid={apiKey}";

        // Make a REST call to OpenWeatherMap API
        return restTemplate.getForObject(
                openWeatherMapGeocodeApiUrl,
                OpenWeatherMapGeocodeResponse.class,
                zipCode,
                countryCode,
                openWeatherMapApiKey
        );
    }

    public OpenWeatherMapResponse getWeatherInfo(double lat, double lon) {
        // Placeholder logic to fetch weather info from OpenWeatherMap API
        String openWeatherMapWeatherApiUrl = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}";

        // Make a REST call to OpenWeatherMap API
        return restTemplate.getForObject(
                openWeatherMapWeatherApiUrl,
                OpenWeatherMapResponse.class,
                lat,
                lon,
                openWeatherMapApiKey
        );
    }

    public String getGeocoderInfo(String zip, String country) {
        try {
            String openWeatherMapApiUrl = "http://api.openweathermap.org/geo/1.0/zip?zip={zip},{country}&appid={apiKey}";

            // Make a REST call to the OpenWeatherMap Geocoder API
            return restTemplate.getForObject(
                    openWeatherMapApiUrl,
                    String.class,
                    zip,
                    country,
                    openWeatherMapApiKey
            );
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return null; // Handle the exception and return an appropriate response
        }
    }
}
