package com.example.SDA_Project.BackEnd;

import com.example.SDA_Project.BussinessLogic.AirQualityData;
import com.example.SDA_Project.BussinessLogic.WeatherData;
import com.example.SDA_Project.BussinessLogic.ForecastData;

import java.sql.*;

public class Database implements CacheManagement{
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
    private double pm2;
    private double pm10;
    private double nh3;
    public void Database(){}
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

    public double getPm2() {
        return pm2;
    }

    public double getPm10() {
        return pm10;
    }

    public double getNh3() {
        return nh3;
    }
    public void connect(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherapp","root","");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void storeWeatherDb(WeatherData data){
        city = data.getCity();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherapp", "root", "");
            String query = "INSERT INTO weather_data (city, temp, main, descrip, wind, pressure, humidity, visibility, feels_like, min_temp, max_temp, sun_rise, sun_set) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, city);
            preparedStatement.setString(2, String.format("%.2f", data.getTemperature()));
            preparedStatement.setString(3, data.getMain());
            preparedStatement.setString(4, data.getDescription());
            preparedStatement.setDouble(5, data.getWind());
            preparedStatement.setDouble(6, data.getPressure());
            preparedStatement.setDouble(7, data.getHumidity());
            preparedStatement.setDouble(8, data.getVisibility());
            preparedStatement.setString(9, String.format("%.2f", data.getFeelsLike()));
            preparedStatement.setString(10, String.format("%.2f", data.getMinTemp()));
            preparedStatement.setString(11, String.format("%.2f", data.getMaxTemp()));
            preparedStatement.setString(12, data.getSunrise());
            preparedStatement.setString(13, data.getSunset());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void storeAirDb(AirQualityData data) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherapp", "root", "");
            String query = "INSERT INTO air_pollution (city, aqi, co, no, no2, o3, so2, pm2, pm10, nh3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, city);
            preparedStatement.setInt(2, data.getAqi());
            preparedStatement.setDouble(3, data.getCo());
            preparedStatement.setDouble(4, data.getNo());
            preparedStatement.setDouble(5, data.getNo2());
            preparedStatement.setDouble(6, data.getO3());
            preparedStatement.setDouble(7, data.getSo2());
            preparedStatement.setDouble(8, data.getPm2());
            preparedStatement.setDouble(9, data.getPm10());
            preparedStatement.setDouble(10, data.getNh3());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void storeForecastDb(ForecastData data) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherapp", "root", "");
            String query = "INSERT INTO forecast (city, date1, date2, date3, date4, date5, min1, min2, min3, min4, min5, max1, max2, max3, max4, max5, main1, main2, main3, main4, main5) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String[][] forecast = data.getForecast();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, city);
            preparedStatement.setString(2, forecast[0][0]);
            preparedStatement.setString(7, String.format("%.2f", Double.parseDouble(forecast[0][1])));
            preparedStatement.setString(12, String.format("%.2f", Double.parseDouble(forecast[0][2])));
            preparedStatement.setString(17, forecast[0][3]);
            preparedStatement.setString(3, forecast[1][0]);
            preparedStatement.setString(8, String.format("%.2f", Double.parseDouble(forecast[1][1])));
            preparedStatement.setString(13, String.format("%.2f", Double.parseDouble(forecast[1][2])));
            preparedStatement.setString(18, forecast[1][3]);
            preparedStatement.setString(4, forecast[2][0]);
            preparedStatement.setString(9, String.format("%.2f", Double.parseDouble(forecast[2][1])));
            preparedStatement.setString(14, String.format("%.2f", Double.parseDouble(forecast[2][2])));
            preparedStatement.setString(19, forecast[2][3]);
            preparedStatement.setString(5, forecast[3][0]);
            preparedStatement.setString(10, String.format("%.2f", Double.parseDouble(forecast[3][1])));
            preparedStatement.setString(15, String.format("%.2f", Double.parseDouble(forecast[3][2])));
            preparedStatement.setString(20, forecast[3][3]);
            preparedStatement.setString(6, forecast[4][0]);
            preparedStatement.setString(11, String.format("%.2f", Double.parseDouble(forecast[4][1])));
            preparedStatement.setString(16, String.format("%.2f", Double.parseDouble(forecast[4][2])));
            preparedStatement.setString(21, forecast[4][3]);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Boolean recordExist(String city){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherapp", "root", "");
            String query = "SELECT COUNT(*) FROM weather_data WHERE city = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, city);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            System.out.println("Error checking record existence.");
            e.printStackTrace();
        }
        return false;
    }
    private void fetchWeatherData(String city) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherapp", "root", "");
            String query = "SELECT * FROM weather_data WHERE city = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, city);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.temperature = resultSet.getDouble("temp");
                this.main = resultSet.getString("main");
                this.description = resultSet.getString("descrip");
                this.wind = resultSet.getDouble("wind");
                this.pressure = resultSet.getDouble("pressure");
                this.humidity = resultSet.getDouble("humidity");
                this.visibility = resultSet.getDouble("visibility");
                this.feelsLike = resultSet.getDouble("feels_like");
                this.minTemperature = resultSet.getDouble("min_temp");
                this.maxTemperature = resultSet.getDouble("max_temp");
                this.sunrise = resultSet.getString("sun_rise");
                this.sunset = resultSet.getString("sun_set");
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchAirPollutionData(String city) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherapp", "root", "");
            String query = "SELECT * FROM air_pollution WHERE city = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, city);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.aqi = resultSet.getInt("aqi");
                this.co = resultSet.getDouble("co");
                this.no = resultSet.getDouble("no");
                this.no2 = resultSet.getDouble("no2");
                this.o3 = resultSet.getDouble("o3");
                this.so2 = resultSet.getDouble("so2");
                this.pm2 = resultSet.getDouble("pm2");
                this.pm10 = resultSet.getDouble("pm10");
                this.nh3 = resultSet.getDouble("nh3");
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void fetchForecastData(String city) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherapp", "root", "");
            String query = "SELECT * FROM forecast WHERE city = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, city);
            ResultSet resultSet = preparedStatement.executeQuery();
            int i = 0;
            while (resultSet.next() && i < 5) {
                forecast[i][0] = resultSet.getString("date" + (i + 1));
                forecast[i][1] = resultSet.getString("min" + (i + 1));
                forecast[i][2] = resultSet.getString("max" + (i + 1));
                forecast[i][3] = resultSet.getString("main" + (i + 1));
                i++;
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            this.city = city;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void fetchData(String city) {
        fetchWeatherData(city);
        fetchAirPollutionData(city);
        fetchForecastData(city);
    }
}
