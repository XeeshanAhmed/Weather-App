package com.example.SDA_Project.FrontEnd;

import com.example.SDA_Project.BackEnd.CacheManagement;
import com.example.SDA_Project.BackEnd.FileHandling;
import com.example.SDA_Project.BussinessLogic.WeatherData;

public class Weather {
    private String City;
    private double longitude,latitude;
    private String Main;
    private String description;
    private double temp;
    private double pressure;
    private double humidity;
    private double visibility;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private String sunSet;
    private String sunRise;
    FileHandling fileHandling=new FileHandling();
    WeatherData weatherData=new WeatherData();

    public void SetLocation(String City)
    {
        this.City=City;
    }
    public void SetLocation(double longitude,double latitude)
    {
        weatherData.fetchWeatherData(longitude,latitude);
    }
    public void getCurrentWeather()
    {
        if(fileHandling.recordExist(City))
        {
            fileHandling.fetchData(City);
            this.Main=fileHandling.getMain();
            this.description=fileHandling.getDescription();
            this.temp=fileHandling.getTemperature();
            this.pressure=fileHandling.getPressure();
            this.humidity=fileHandling.getHumidity();
            this.visibility=fileHandling.getVisibility();
//            System.out.println("Han bhai file se data utha liya h");
        }
        else
        {
            weatherData.fetchWeatherData(City);
            fileHandling.storeWeatherDb(weatherData);
            this.Main=weatherData.getMain();
            this.description=weatherData.getDescription();
            this.temp=weatherData.getTemperature();
            this.pressure=weatherData.getPressure();
            this.humidity=weatherData.getHumidity();
            this.visibility=weatherData.getVisibility();
        }

    }
    public void getBasicInformation()
    {

        if(fileHandling.recordExist(City))
        {
            fileHandling.fetchData(City);
            this.feelsLike=fileHandling.getFeelsLike();
            this.tempMin=fileHandling.getMinTemperature();
            this.tempMax=fileHandling.getMaxTemperature();
        }
        else
        {
            this.feelsLike=weatherData.getFeelsLike();
            this.tempMin=weatherData.getMinTemp();
            this.tempMax=weatherData.getMaxTemp();
        }

    }
    public void getSunInfo()
    {

        if(fileHandling.recordExist(City))
        {
            this.sunRise=fileHandling.getSunrise();
            this.sunSet=fileHandling.getSunset();
        }
        else
        {
            this.sunRise=weatherData.getSunrise();
            this.sunSet=weatherData.getSunset();
        }

    }
    public void showCurrentWeatherConditions()
    {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t--------------------------------");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|             "+Main);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|         "+description);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|   Temperature : "+String.format("%.2f",temp)+"°C");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|      Pressure : "+pressure+"hPa");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|          Wind : "+weatherData.getWind()+"m/s N");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|      Humidity : "+humidity+"%");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|    Visibility : "+visibility+"km");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t--------------------------------");

    }
    public void showBasicInformation()
    {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t--------------------------------------");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|            Feels Like : "+String.format("%.2f",feelsLike)+"°C");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|   Minimum Temperature : ~"+String.format("%.2f",tempMin)+"°C");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|   Maximum Temperature : ~"+String.format("%.2f",tempMax)+"°C");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|               "+weatherData.getTimeStamp());
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t--------------------------------------");
    }
    public void showSunSetRiseInfo()
    {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t----------------------------");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|   Sun Rise Time : "+sunRise);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|   Sun Set Time : "+sunSet);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t----------------------------");
    }

}


