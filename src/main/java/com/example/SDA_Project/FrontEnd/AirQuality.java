package com.example.SDA_Project.FrontEnd;

import com.example.SDA_Project.BackEnd.FileHandling;
import com.example.SDA_Project.BussinessLogic.AirQualityData;

public class AirQuality {
    private String City;
    private double longitude,latitude;
    private int aqi;
    String pollutionLevel;
    String pollutionCategory;
    private double co;
    private double no;
    private double no2;
    private double o3;
    private double so2;
    private double pm25;
    private double pm10;
    private double nh3;

    FileHandling fileHandling=new FileHandling();

    AirQualityData airQualityData=new AirQualityData();

    //    public void SetLocation(String City)
//     {
//         airQualityData.setLocation(City);
//     }
//     public void SetLocation(double longitude,double latitude)
//     {
//         airQualityData.fetchAirPollutionData(longitude,latitude);
//     }
    public void getAirPollutionData(String City)
    {
        this.City=City;
        if(fileHandling.recordExist(City))
        {
            fileHandling.fetchData(City);
            this.aqi= fileHandling.getAqi();
            this.co= fileHandling.getCo();
            this.no= fileHandling.getNo();
            this.nh3= fileHandling.getNh3();
            this.no2= fileHandling.getNo2();
            this.o3= fileHandling.getO3();
            this.so2= fileHandling.getSo2();
            this.pm10= fileHandling.getPm10();
            this.pm25= fileHandling.getPm25();
        }
        else {
            airQualityData.fetchAirPollutionData(this.City);
            fileHandling.storeAirDb(airQualityData);
            this.aqi=airQualityData.getAqi();
            this.pollutionLevel= airQualityData.getPollutionLevel();
            this.pollutionCategory= airQualityData.getPollutionCategory();
            this.co= airQualityData.getCo();
            this.no= airQualityData.getNo();
            this.nh3= airQualityData.getNh3();
            this.no2= airQualityData.getNo2();
            this.o3= airQualityData.getO3();
            this.so2= airQualityData.getSo2();
            this.pm10= airQualityData.getPm10();
            this.pm25= airQualityData.getPm2();
            fileHandling.storeAirDb(airQualityData);
        }

    }
    public void showAirPollutionData()
    {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t------------------------------");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|         AQI : "+this.aqi);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|   AQI Level : "+this.pollutionLevel);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|    Category : "+this.pollutionCategory);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t------------------------------");
    }

    public void showGassesData()
    {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t-------------------");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|     CO : "+this.co);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|     NO : "+this.no);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|    NO₂ : "+this.no2);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|     O₃ : "+this.o3);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|    SO₂ : "+this.so2);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|  PM2.5 : "+this.pm25);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|   PM10 : "+this.pm10);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|    NH₃ : "+this.nh3);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t-------------------");
    }

}

