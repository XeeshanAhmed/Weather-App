package com.example.SDA_Project.BussinessLogic;

public class ForecastData {
    private WeatherAPIHandler handler;

    public ForecastData() {
        handler = new WeatherAPIHandler();
    }

    public void fetchForecast(String city) {
        handler.sendForecastRequest(city);
    }

    public void fetchForecast(double lon, double lat) {
        handler.sendForecastRequest(lon, lat);
    }

    public String[][] getForecast() {
        return handler.getForeCast();
    }
}
