package com.example.tool_agumented_chatbot.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@Configuration
public class WeatherService {

    /**
     * A record to hold structured weather data.
     */
    public record WeatherRequest(String city) {}
    public record WeatherResponse(double temperature, String unit, String condition) {}

    @Bean
    @Description("Get the current weather for a specific city") // <-- This description is critical for the LLM!
    public Function<WeatherRequest, WeatherResponse> getCurrentWeather() {
        return (request) -> {
            System.out.println("EXECUTING TOOL: Getting weather for city: " + request.city());

            // In a real app, you would call a real weather API here.
            // For this example, we'll return mock data.
            if (request.city().equalsIgnoreCase("Chennai")) {
                // It's late evening in Chennai on a September night, so it's probably warm.
                return new WeatherResponse(29.0, "Celsius", "Partly Cloudy");
            } else if (request.city().equalsIgnoreCase("London")) {
                return new WeatherResponse(14.0, "Celsius", "Light Rain");
            } else {
                return new WeatherResponse(22.0, "Celsius", "Sunny");
            }
        };
    }
}