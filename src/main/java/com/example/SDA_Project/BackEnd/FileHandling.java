package com.example.SDA_Project.BackEnd;

import com.example.SDA_Project.BussinessLogic.AirQualityData;
import com.example.SDA_Project.BussinessLogic.ForecastData;
import com.example.SDA_Project.BussinessLogic.WeatherData;

import java.io.*;


public class FileHandling implements CacheManagement{
    String city;
    private double temperature;
    private String main;
    private String description;
    private double wind;
    private double pressure;
    private double humidity;
    private double visibility;
    private double feelsLike;
    private double minTemperature;
    private double maxTemperature;
    private String sunrise;
    private String sunset;
    private String[][] forecast = new String[5][4];
    private int aqi;
    private double co;
    private double no;
    private double no2;
    private double o3;
    private double so2;
    private double pm25;
    private double pm10;
    private double nh3;
    public double getTemperature() {
        return temperature;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public double getWind() {
        return wind;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getVisibility() {
        return visibility;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String[][] getForecast() {
        return forecast;
    }

    public int getAqi() {
        return aqi;
    }

    public double getCo() {
        return co;
    }

    public double getNo() {
        return no;
    }

    public double getNo2() {
        return no2;
    }

    public double getO3() {
        return o3;
    }

    public double getSo2() {
        return so2;
    }

    public double getPm25() {
        return pm25;
    }

    public double getPm10() {
        return pm10;
    }

    public double getNh3() {
        return nh3;
    }
    private static final String WEATHERDATA_TXT = "weatherdata.txt";
    private static final String AIRQUALITYDATA_TXT = "airqualitydata.txt";
    private static final String FORECAST_TXT = "forecast.txt";

    @Override
    public void connect() {

    }

    @Override
    public void storeWeatherDb(WeatherData data) {
        try {
            File file = new File("weatherdata.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("weatherdata.txt", true))) {
                this.city=data.getCity();
                writer.write(data.getCity() + "," +
                        String.format("%.2f", data.getTemperature()) + "," +
                        data.getMain() + "," +
                        data.getDescription() + "," +
                        String.format("%.2f", data.getWind()) + "," +
                        String.format("%.2f", data.getPressure()) + "," +
                        String.format("%.2f", data.getHumidity()) + "," +
                        String.format("%.2f", data.getVisibility()) + "," +
                        String.format("%.2f", data.getFeelsLike()) + "," +
                        String.format("%.2f", data.getMinTemp()) + "," +
                        String.format("%.2f", data.getMaxTemp()) + "," +
                        data.getSunrise() + "," +
                        data.getSunset());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void storeAirDb(AirQualityData data) {
        try {

            File file = new File("airqualitydata.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("airqualitydata.txt", true))) {
                this.city=data.getCity();
                writer.write(this.city + "," +
                        data.getAqi() + "," +
                        data.getCo() + "," +
                        data.getNo() + "," +
                        data.getNo2() + "," +
                        data.getO3() + "," +
                        data.getSo2() + "," +
                        data.getPm2() + "," +
                        data.getPm10() + "," +
                        data.getNh3());
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void storeForecastDb(ForecastData data) {
        try {

            File file = new File("forecast.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("forecast.txt", true))) {
                String[][] forecast = data.getForecast();
                for (int i = 0; i < forecast.length; i++) {
                    for (int j = 0; j < forecast[i].length; j++) {
                        writer.write(forecast[i][j]);
                        writer.write(",");
                    }
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Boolean recordExist(String city) {
        try (BufferedReader reader = new BufferedReader(new FileReader("weatherdata.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(city)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void fetchWeatherData(String city)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("weatherdata.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(city)) {
                    this.city = parts[0];
                    this.temperature = Double.parseDouble(parts[1]);
                    this.main = parts[2];
                    this.description = parts[3];
                    this.wind = Double.parseDouble(parts[4]);
                    this.pressure = Double.parseDouble(parts[5]);
                    this.humidity = Double.parseDouble(parts[6]);
                    this.visibility = Double.parseDouble(parts[7]);
                    this.feelsLike = Double.parseDouble(parts[8]);
                    this.minTemperature = Double.parseDouble(parts[9]);
                    this.maxTemperature = Double.parseDouble(parts[10]);
                    this.sunrise = parts[11];
                    this.sunset = parts[12];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void fetchAirPollutionData()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("airqualitydata.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(city)) {
                    this.city = parts[0];
                    this.aqi = Integer.parseInt(parts[1]);
                    this.co = Double.parseDouble(parts[2]);
                    this.no = Double.parseDouble(parts[3]);
                    this.no2 = Double.parseDouble(parts[4]);
                    this.o3 = Double.parseDouble(parts[5]);
                    this.so2 = Double.parseDouble(parts[6]);
                    this.pm25 = Double.parseDouble(parts[7]);
                    this.pm10 = Double.parseDouble(parts[8]);
                    this.nh3 = Double.parseDouble(parts[9]);
                    break;
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fetchForecastData(String city) {
        try (BufferedReader reader = new BufferedReader(new FileReader("forecast.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(city)) {

                    for (int i = 0; i < 5; i++) {
                        int index = i * 4 + 1;
                        forecast[i][0] = parts[index];
                        forecast[i][1] = parts[index + 1];
                        forecast[i][2] = parts[index + 2];
                        forecast[i][3] = parts[index + 3];
                    }
                    this.city = city;
                    break;
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fetchData(String city) {
        fetchWeatherData(city);
        fetchAirPollutionData();
        //fetchForecastData(city);
    }
}
