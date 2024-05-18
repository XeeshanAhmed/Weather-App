package com.example.SDA_Project.BussinessLogic;

public class WeatherData {
    private String city;
    private float temperature;
    private String description;
    private WeatherAPIHandler handler;

    public WeatherData() {
        this.handler = new WeatherAPIHandler();
    }

    public void fetchWeatherData(String city) {
        this.city = city;
        handler.sendWeatherRequest(city);
        updateWeatherData();
    }

    public void fetchWeatherData(double lon, double lat) {
        handler.sendWeatherRequest(lon, lat);
        this.city = handler.getCity();
        updateWeatherData();
    }

    private void updateWeatherData() {
        temperature = handler.getTemp();
        description = handler.getDescrip();
    }

    public String getCity() {
        return city;
    }

    public float getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    public float getFeelsLike() {
        return handler.getFeelsLike();
    }

    public float getMinTemp() {
        return handler.getMinTemp();
    }

    public float getMaxTemp() {
        return handler.getMaxTemp();
    }

    public String getMain() {
        return handler.getMain();
    }

    public String getDescrip() {
        return handler.getDescrip();
    }

    public String getSunrise() {
        return handler.getSrt();
    }

    public String getSunset() {
        return handler.getSst();
    }

    public double getWind() {
        return handler.getWind();
    }

    public double getHumidity() {
        return handler.getHumid();
    }

    public double getPressure() {
        return handler.getPress();
    }

    public double getVisibility() {
        return handler.getVisib();
    }

    public String getTimeStamp() {
        return handler.getTimeStamp();
    }

    public String getIcon() {
        return handler.getIcon();
    }
}
