package com.example.SDA_Project.BussinessLogic;

public class AirQualityData {
    private int aqi;
    private String city;
    private String pollutionLevel;
    private String pollutionCategory;
    private WeatherAPIHandler handler;

    public AirQualityData() {
        handler = new WeatherAPIHandler();
    }

    public void fetchAirPollutionData(String city) {
        this.city=city;
        handler.updateCoordinatesFromCity(city);
        handler.sendAirRequest();

        updateAirQualityData();
    }

    public void fetchAirPollutionData(double lon, double lat) {
        handler.sendAirRequest(lon, lat);

        updateAirQualityData();
    }

    private void updateAirQualityData() {
        aqi = handler.getAqi();
        pollutionLevel = handler.getPollLev();
        pollutionCategory = handler.getPollCateg();
    }
    public String getCity()
    {
        return this.city;
    }

    public int getAqi() {
        return aqi;
    }

    public String getPollutionLevel() {
        return pollutionLevel;
    }

    public String getPollutionCategory() {
        return pollutionCategory;
    }

    public double getCo() {
        return handler.getCo();
    }

    public double getNo() {
        return handler.getNo();
    }

    public double getNo2() {
        return handler.getNo2();
    }

    public double getO3() {
        return handler.getO3();
    }

    public double getSo2() {
        return handler.getSo2();
    }

    public double getPm2() {
        return handler.getPm2();
    }

    public double getPm10() {
        return handler.getPm10();
    }

    public double getNh3() {
        return handler.getNh3();
    }
}
