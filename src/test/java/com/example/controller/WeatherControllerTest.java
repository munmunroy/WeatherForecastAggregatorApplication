package com.example.controller;

import com.example.dto.WeatherAggregationResponse;
import com.example.service.WeatherAggregatorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {
    @Mock
    private WeatherAggregatorService weatherAggregatorService;

    @InjectMocks
    private WeatherController weatherController;

    @Test
    public void testGetWeather() {
        // Mock the response from the WeatherAggregatorService
        when(weatherAggregatorService.getWeatherAggregation(anyString(), anyString(), anyString()))
                .thenReturn(new WeatherAggregationResponse());

        ResponseEntity<WeatherAggregationResponse> responseEntity =
                weatherController.getWeather("Noida", "110025", "IN");

        // Verify the result
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
