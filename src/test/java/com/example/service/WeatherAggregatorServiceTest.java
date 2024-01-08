package com.example.service;

import com.example.dto.AccuWeatherResponse;
import com.example.dto.OpenWeatherMapGeocodeResponse;
import com.example.dto.OpenWeatherMapResponse;
import com.example.dto.WeatherAggregationResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherAggregatorServiceTest {
    @Mock
    private AccuWeatherService accuWeatherService;

    @Mock
    private OpenWeatherMapService openWeatherMapService;

    @InjectMocks
    private WeatherAggregatorService weatherAggregatorService;

    @Test
    public void testGetWeatherAggregation() {
        // Mock the responses from AccuWeather and OpenWeatherMap services
        when(accuWeatherService.getLocationKey(anyString())).thenReturn("3146227");
        when(accuWeatherService.getWeatherInfo(anyString())).thenReturn(new AccuWeatherResponse());
        when(openWeatherMapService.getGeocodeInfo(anyString(), anyString())).thenReturn(new OpenWeatherMapGeocodeResponse());
        when(openWeatherMapService.getWeatherInfo(anyDouble(), anyDouble())).thenReturn(new OpenWeatherMapResponse());

        WeatherAggregationResponse aggregationResponse =
                weatherAggregatorService.getWeatherAggregation("Noida", "110025", "IN");

        // Verify the result
        assertNotNull(aggregationResponse);
    }
}
