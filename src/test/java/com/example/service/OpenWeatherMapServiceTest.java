package com.example.service;

import com.example.dto.OpenWeatherMapGeocodeResponse;
import com.example.dto.OpenWeatherMapResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherMapServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OpenWeatherMapService openWeatherMapService;

    @Test
    public void testGetGeocodeInfo() {
        // Mock the response from OpenWeatherMap Geocode API
        when(restTemplate.getForObject(anyString(), any()))
                .thenReturn(new OpenWeatherMapGeocodeResponse());

        OpenWeatherMapGeocodeResponse geocodeResponse = openWeatherMapService.getGeocodeInfo("201014", "IN");

        // Verify the result
        assertNotNull(geocodeResponse);
    }

    @Test
    public void testGetWeatherInfo() {
        // Mock the response from OpenWeatherMap Weather API
        when(restTemplate.getForObject(anyString(), any()))
                .thenReturn(new OpenWeatherMapResponse());

        OpenWeatherMapResponse weatherResponse = openWeatherMapService.getWeatherInfo(28.53, 77.39);

        // Verify the result
        assertNotNull(weatherResponse);
    }
}
