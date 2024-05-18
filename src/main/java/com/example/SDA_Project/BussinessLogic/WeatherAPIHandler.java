package com.example.SDA_Project.BussinessLogic;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WeatherAPIHandler {
    @FXML
    private float temp, feels_like, min_temp, max_temp;
    private String main, descrip, srt, sst, time_stamp, poll_lev, poll_categ, city, icon;
    private double wind, co, no, no2, o3, so2, pm2, pm10, nh3;
    private int humid, press, visib, aqi, air_qual;
    private String weatherData, airPollutionData, forecastData;
    private double[] coord = new double[2];
    private String[][] ForeCast = new String[6][4];
    private Map<String, Integer> weatherCounts = new HashMap<>();
    private float minTemp = Float.MAX_VALUE;
    private float maxTemp = Float.MIN_VALUE;

    public String sendGetRequests(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public void sendWeatherRequest(String city) {
        try {
            URL weatherUrl = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=ba63a62d47eed52abfdf1903fbf7ed3a");
            weatherData = sendGetRequests(weatherUrl);
            parseCurrentWeatherData(weatherData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendForecastRequest(String city) {
        try {
            URL forecastUrl = new URL("https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=ba63a62d47eed52abfdf1903fbf7ed3a");
            forecastData = sendGetRequests(forecastUrl);
            parseForecastData(forecastData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAirRequest() {
        try {
            URL airPollutionUrl = new URL("https://api.openweathermap.org/data/2.5/air_pollution?lat=" + coord[0] + "&lon=" + coord[1] + "&appid=ba63a62d47eed52abfdf1903fbf7ed3a");
            airPollutionData = sendGetRequests(airPollutionUrl);
            parseAirPollutionData(airPollutionData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendWeatherRequest(Double lon, Double lat) {
        try {
            URL weatherUrl = new URL("https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=ba63a62d47eed52abfdf1903fbf7ed3a");
            weatherData = sendGetRequests(weatherUrl);
            parseCurrentWeatherData(weatherData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendForecastRequest(Double lon, Double lat) {
        try {
            URL weatherUrl = new URL("https://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&appid=ba63a62d47eed52abfdf1903fbf7ed3a");
            weatherData = sendGetRequests(weatherUrl);
            parseCurrentWeatherData(weatherData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAirRequest(Double lon, Double lat) {
        try {
            URL airPollutionUrl = new URL("https://api.openweathermap.org/data/2.5/air_pollution?lat=" + lat + "&lon=" + lon + "&appid=ba63a62d47eed52abfdf1903fbf7ed3a");
            airPollutionData = sendGetRequests(airPollutionUrl);
            parseAirPollutionData(airPollutionData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String parseDate(long flag) {
        Date date = new Date(flag);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return (sdf.format(date));
    }

    public void parseCurrentWeatherData(String weatherData) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(weatherData, JsonObject.class);
        JsonObject coordObject = jsonObject.getAsJsonObject("coord");

        JsonObject mainObject = jsonObject.getAsJsonObject("main");
        JsonObject windObject = jsonObject.getAsJsonObject("wind");
        JsonObject sysObject = jsonObject.getAsJsonObject("sys");

        JsonArray weatherArray = jsonObject.getAsJsonArray("weather");
        JsonObject weatherObject = weatherArray.get(0).getAsJsonObject();

        icon = weatherObject.get("icon").getAsString();
        temp = mainObject.get("temp").getAsFloat() - 273.15f;
        city = jsonObject.get("name").getAsString();
        main = (weatherObject.get("main").getAsString());
        descrip = (weatherObject.get("description").getAsString());
        wind = (windObject.get("speed").getAsDouble());
        humid = (mainObject.get("humidity").getAsInt());
        press = (mainObject.get("pressure").getAsInt());
        visib = (jsonObject.get("visibility").getAsInt() / 1000);
        feels_like = mainObject.get("feels_like").getAsFloat() - 273.15f;
        min_temp = mainObject.get("temp_min").getAsFloat() - 273.15f;
        max_temp = mainObject.get("temp_max").getAsFloat() - 273.15f;
        long flag = sysObject.get("sunrise").getAsInt() * 1000L;
        srt = parseDate(flag);
        long flag_ = sysObject.get("sunset").getAsInt() * 1000L;
        sst = parseDate(flag_);
        coord[0] = coordObject.get("lon").getAsDouble();
        coord[1] = coordObject.get("lat").getAsDouble();
        city = jsonObject.get("name").getAsString();
        long unixTimestamp = jsonObject.get("dt").getAsInt() * 1000L;
        Date date = new Date(unixTimestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd-MM");
        String formattedDate = sdf.format(date);
        time_stamp = formattedDate;
    }

    public void parseAirPollutionData(String airPollutionData) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(airPollutionData, JsonObject.class);
        JsonObject listObject = jsonObject.getAsJsonArray("list").get(0).getAsJsonObject();
        JsonObject componentsObject = listObject.getAsJsonObject("components");
        air_qual = listObject.getAsJsonObject("main").get("aqi").getAsInt();
        if (air_qual == 1) {
            poll_lev = "Good";
            poll_categ = "Good";
        } else if (air_qual == 2) {
            poll_lev = "Fair";
            poll_categ = "Lightly polluted";
        } else if (air_qual == 3) {
            poll_lev = "Moderate";
            poll_categ = "Moderately polluted";
        } else if (air_qual == 4) {
            poll_lev = "Poor";
            poll_categ = "Heavily polluted";
        } else {
            poll_lev = "Very Poor";
            poll_categ = "Severely polluted";
        }
        long flag = listObject.get("dt").getAsInt() * 1000L;
        time_stamp = parseDate(flag);
        aqi = air_qual;
        co = componentsObject.get("co").getAsDouble();
        no = componentsObject.get("no").getAsDouble();
        no2 = componentsObject.get("no2").getAsDouble();
        o3 = componentsObject.get("o3").getAsDouble();
        so2 = componentsObject.get("so2").getAsDouble();
        pm2 = componentsObject.get("pm2_5").getAsDouble();
        pm10 = componentsObject.get("pm10").getAsDouble();
        nh3 = componentsObject.get("nh3").getAsDouble();
    }
    public void parseForecastData(String forecastData) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(forecastData, JsonObject.class);
        JsonArray listArray = jsonObject.getAsJsonArray("list");
        String date = "";
        int ind = 0;
        for (int i = 0; i < listArray.size(); i++) {
            JsonObject listObject = listArray.get(i).getAsJsonObject();
            String dateText = listObject.get("dt_txt").getAsString();
            String currentDate = dateText.substring(0, 10);
            if (!currentDate.equals(date)) {
                if (!date.isEmpty()) {
                    ForeCast[ind][0] = date;
                    ForeCast[ind][1] = String.valueOf(minTemp);
                    ForeCast[ind][2] = String.valueOf(maxTemp);
                    ForeCast[ind][3] = getMostFrequentWeatherCondition(weatherCounts);
                    ind++;
                }
                date = currentDate;
                weatherCounts.clear();
                minTemp = Float.MAX_VALUE;
                maxTemp = Float.MIN_VALUE;
            }
            JsonArray weatherArray = listObject.getAsJsonArray("weather");
            for (int j = 0; j < weatherArray.size(); j++) {
                JsonObject weatherObject = weatherArray.get(j).getAsJsonObject();
                String mainWeather = weatherObject.get("main").getAsString();
                weatherCounts.put(mainWeather, weatherCounts.getOrDefault(mainWeather, 0) + 1);
            }
            JsonObject listMain = listObject.get("main").getAsJsonObject();
            float tempMax = listMain.get("temp_max").getAsFloat() - 273.15f;
            float tempMin = listMain.get("temp_min").getAsFloat() - 273.15f;
            if (tempMax > maxTemp) {
                maxTemp = tempMax;
            }
            if (tempMin < minTemp) {
                minTemp = tempMin;
            }
        }
        if (!date.isEmpty()) {
            ForeCast[ind][0] = date;
            ForeCast[ind][1] = String.valueOf(minTemp);
            ForeCast[ind][2] = String.valueOf(maxTemp);
            ForeCast[ind][3] = getMostFrequentWeatherCondition(weatherCounts);
        }
    }

    private String getMostFrequentWeatherCondition(Map<String, Integer> weatherCounts) {
        String mostFrequentCondition = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : weatherCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequentCondition = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return mostFrequentCondition;
    }

    public double getLong() {
        return coord[0];
    }

    public String getCity() {
        return city;
    }

    public float getTemp() {
        return temp;
    }

    public float getFeelsLike() {
        return feels_like;
    }

    public float getMinTemp() {
        return min_temp;
    }

    public float getMaxTemp() {
        return max_temp;
    }

    public String getMain() {
        return main;
    }

    public String getDescrip() {
        return descrip;
    }

    public String getSrt() {
        return srt;
    }

    public String getSst() {
        return sst;
    }

    public double getWind() {
        return wind;
    }

    public int getHumid() {
        return humid;
    }

    public int getPress() {
        return press;
    }

    public int getVisib() {
        return visib;
    }

    public String getTimeStamp() {
        return time_stamp;
    }

    public String getPollLev() {
        return poll_lev;
    }

    public String getPollCateg() {
        return poll_categ;
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

    public int getAqi() {
        return aqi;
    }

    public int getAirQual() {
        return air_qual;
    }

    public String[][] getForeCast() {
        return ForeCast;
    }

    public String getWeatherData() {
        return weatherData;
    }

    public String getIcon() {
        try {
            JsonObject jsonObject = new Gson().fromJson(weatherData, JsonObject.class);
            JsonArray weatherArray = jsonObject.getAsJsonArray("weather");
            JsonObject weatherObject = weatherArray.get(0).getAsJsonObject();
            return weatherObject.get("icon").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void updateCoordinatesFromCity(String city) {
        try {
            URL geocodingUrl = new URL("https://api.openweathermap.org/geo/1.0/direct?q=" + city + "&appid=ba63a62d47eed52abfdf1903fbf7ed3a");
            String geocodingData = sendGetRequests(geocodingUrl);
            parseGeocodingData(geocodingData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseGeocodingData(String geocodingData) {
        Gson gson = new Gson();
        JsonArray geocodingArray = gson.fromJson(geocodingData, JsonArray.class);

        if (geocodingArray.size() > 0) {
            JsonObject firstResult = geocodingArray.get(0).getAsJsonObject(); // Get the first result
            coord[0] = firstResult.get("lon").getAsDouble(); // Update longitude
            coord[1] = firstResult.get("lat").getAsDouble(); // Update latitude
        } else {
            System.out.println("Error: City not found.");
        }
    }

    public void triggerAction(String city) {
        try {
            URL triggerUrl = new URL("http://api.openweathermap.org/data/3.0/triggers/5852816a9aaacb00153134a3");
            HttpURLConnection connection = (HttpURLConnection) triggerUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
