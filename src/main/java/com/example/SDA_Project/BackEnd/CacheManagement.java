package com.example.SDA_Project.BackEnd;

import com.example.SDA_Project.BussinessLogic.AirQualityData;
import com.example.SDA_Project.BussinessLogic.ForecastData;
import com.example.SDA_Project.BussinessLogic.WeatherData;

public interface CacheManagement {
    public void connect();
    public void storeWeatherDb(WeatherData data);
    public void storeAirDb(AirQualityData data);
    public void storeForecastDb(ForecastData data);
    public Boolean recordExist(String city);
    public void fetchForecastData(String city);
    public void fetchData(String city);
}
