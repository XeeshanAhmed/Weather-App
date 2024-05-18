package com.example.SDA_Project.BussinessLogic;

import java.util.Objects;

public class WeatherNotification {
    private String[] notif = new String[2];
    private WeatherAPIHandler handler;

    public WeatherNotification() {
        handler = new WeatherAPIHandler();
    }

    public String[] getNotifications(String city) {
        int i = 0;

        handler.sendWeatherRequest(city);

        if (Objects.equals(handler.getMain(), "Clouds") || Objects.equals(handler.getMain(), "Rain")) {
            notif[i] = "Poor Weather Conditions Detected. Take necessary precautions.";
            i++;
        }

        handler.updateCoordinatesFromCity(city);
        handler.sendAirRequest();

        if (handler.getAqi() > 3) {
            notif[i] = "Poor Air Quality Detected. Consider limiting outdoor activities.";
            i++;
        }

        return notif;
    }
}
