package com.example.SDA_Project.FrontEnd;

import com.example.SDA_Project.BussinessLogic.ForecastData;

public class Forecast {
    private String City;
    private double longitude,latitude;
    private String[][] forecast;
    ForecastData forecastData=new ForecastData();
    public void setLocation(String City)
    {
        this.City=City;
        forecastData.fetchForecast(this.City);
    }
    public void setLocation(double lon, double lat)
    {
        this.longitude=lon;
        this.latitude=lat;
        forecastData.fetchForecast(this.longitude,this.latitude);
    }

    public void setForecastData() {
        forecast=forecastData.getForecast();
    }
    public void showForecast()
    {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t----------------------------------------------------");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tDate\t\tMinimum\t\tMaximum\t\tForecast");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t----------------------------------------------------");
        for (int i = 0; i < forecast.length; i++) {
            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
            for (int j = 0; j < forecast[i].length; j++) {
                System.out.print(forecast[i][j]+"\t");

            }
            System.out.println(" ");
        }
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t----------------------------------------------------");

    }
}
