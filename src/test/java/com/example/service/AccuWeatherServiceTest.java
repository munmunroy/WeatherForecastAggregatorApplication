package com.example.service;

import com.example.dto.AccuWeatherLocationResponse;
import com.example.dto.AccuWeatherResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class AccuWeatherServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AccuWeatherService accuWeatherService;

    @Test
    public void testGetLocationKey() {
        // Mock the response from AccuWeather API
        when(restTemplate.getForObject(anyString(), any()))
                .thenReturn(new AccuWeatherLocationResponse("3146227"));

        String locationKey = accuWeatherService.getLocationKey("Noida");

        // Verify the result
        assertEquals("3146227", locationKey);
    }

    @Test
    public void testGetWeatherInfo() {
        // Mock the response from AccuWeather API
        when(restTemplate.getForObject(anyString(), any()))
                .thenReturn(new AccuWeatherResponse());

        AccuWeatherResponse weatherResponse = accuWeatherService.getWeatherInfo("3146227");

        // Verify the result
        assertNotNull(weatherResponse);
    }
}
