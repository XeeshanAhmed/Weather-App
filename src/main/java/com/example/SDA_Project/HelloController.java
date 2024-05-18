package com.example.SDA_Project;
import com.example.SDA_Project.BackEnd.Database;
import com.example.SDA_Project.BussinessLogic.AirQualityData;
import com.example.SDA_Project.BussinessLogic.ForecastData;
import com.example.SDA_Project.BussinessLogic.WeatherData;
import com.example.SDA_Project.BussinessLogic.WeatherNotification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.sql.Connection;
import java.sql.DriverManager;

public class HelloController {
    @FXML
    private ChoiceBox<String> myChoiceBox;
    @FXML
    private AnchorPane app;
    @FXML
    private Pane notif_pane;
    @FXML
    private ImageView temp_icon;
    @FXML
    private TextField longit, latit, city;
    @FXML
    private Button search_btn;
    @FXML
    private Label city_name, temp, main, descrip, wind, humid, press, visib, feels_like, min_temp, max_temp, srt, sst, date1, max1, min1, main1, date2, max2, min2, main2, date3, max3, min3, main3, date4, max4, min4, main4, date5, max5, min5, main5, aqi, poll_lev, poll_categ, time_stamp, co, no, no2, o3, so2, pm2, pm10, nh3, notif, notif_dot;
    private Background background;
    private BackgroundImage backgroundImage;
    BackgroundSize backgroundSize;
    private Image image;
    private String[] search = {"Search By City.", "Search By Coordinates"};
    private String[] notification = new String[2];
    Database db = new Database();
    public Image setBackground(String weather){
        Image image1;
        if (weather.equals("Clear")) {
            image1 = new Image(getClass().getResource("/com/example/SDA_Project/clear.jpg").toString(), true);
        } else if (weather.equals("Clouds")) {
            image1 = new Image(getClass().getResource("/com/example/SDA_Project/clouds.jpg").toString(), true);
        } else if (weather.equals("Rain")) {
            image1 = new Image(getClass().getResource("/com/example/SDA_Project/rain.jpg").toString(), true);
        } else if (weather.equals("Snow")) {
            image1 = new Image(getClass().getResource("/com/example/SDA_Project/snow.jpg").toString(), true);
        } else if (weather.equals("Fog") || weather.equals("Mist")) {
            image1 = new Image(getClass().getResource("/com/example/SDA_Project/fog.jpg").toString(), true);
        } else if (weather.equals("Smoke")) {
            image1 = new Image(getClass().getResource("/com/example/SDA_Project/smoke.jpg").toString(), true);
        } else if (weather.equals("Haze")) {
            image1 = new Image(getClass().getResource("/com/example/SDA_Project/haze.jpg").toString(), true);
        } else if (weather.equals("Dust")) {
            image1 = new Image(getClass().getResource("/com/example/SDA_Project/dust.jpg").toString(), true);
        } else {
            image1 = new Image(getClass().getResource("/com/example/SDA_Project/clear.jpg").toString(), true);
        }
        return image1;
    }
    public void setCurrWeather(WeatherData weatherData){
        Image image1 = new Image("https://openweathermap.org/img/wn/" + weatherData.getIcon() + "@2x.png");
        temp_icon.setImage(image1);
        temp.setText(String.format("%.2f", weatherData.getTemperature()));
        main.setText(weatherData.getMain());
        descrip.setText(weatherData.getDescrip());
        wind.setText(String.valueOf(weatherData.getWind()));
        humid.setText(String.valueOf(weatherData.getHumidity()));
        press.setText(String.valueOf(weatherData.getPressure()));
        visib.setText(String.valueOf(weatherData.getVisibility()));

        feels_like.setText(String.format("%.2f", weatherData.getFeelsLike()));
        min_temp.setText(String.format("%.2f", weatherData.getMinTemp()));
        max_temp.setText(String.format("%.2f", weatherData.getMaxTemp()));

        srt.setText(weatherData.getSunrise());
        sst.setText(weatherData.getSunset());

        time_stamp.setText(weatherData.getTimeStamp());
    }
    public void setForeCast(ForecastData foreCast){
        String foreCastArray[][] = foreCast.getForecast();
        date1.setText(String.valueOf(foreCastArray[0][0]));
        max1.setText(String.format("%.2f", Double.parseDouble(foreCastArray[0][1])));
        min1.setText(String.format("%.2f", Double.parseDouble(foreCastArray[0][2])));
        main1.setText(String.valueOf(foreCastArray[0][3]));

        date2.setText(String.valueOf(foreCastArray[1][0]));
        max2.setText(String.format("%.2f", Double.parseDouble(foreCastArray[1][1])));
        min2.setText(String.format("%.2f", Double.parseDouble(foreCastArray[1][2])));
        main2.setText(String.valueOf(foreCastArray[1][3]));

        date3.setText(String.valueOf(foreCastArray[2][0]));
        max3.setText(String.format("%.2f", Double.parseDouble(foreCastArray[2][1])));
        min3.setText(String.format("%.2f", Double.parseDouble(foreCastArray[2][2])));
        main3.setText(String.valueOf(foreCastArray[2][3]));

        date4.setText(String.valueOf(foreCastArray[3][0]));
        max4.setText(String.format("%.2f", Double.parseDouble(foreCastArray[3][1])));
        min4.setText(String.format("%.2f", Double.parseDouble(foreCastArray[3][2])));
        main4.setText(String.valueOf(foreCastArray[3][3]));

        date5.setText(String.valueOf(foreCastArray[4][0]));
        max5.setText(String.format("%.2f", Double.parseDouble(foreCastArray[4][1])));
        min5.setText(String.format("%.2f", Double.parseDouble(foreCastArray[4][2])));
        main5.setText(String.valueOf(foreCastArray[4][3]));
    }
    public void setAirQuality(AirQualityData AQD){
        aqi.setText(String.valueOf(AQD.getAqi()));
        poll_lev.setText(AQD.getPollutionLevel());
        poll_categ.setText(AQD.getPollutionCategory());

        co.setText(String.valueOf(AQD.getCo()));
        no.setText(String.valueOf(AQD.getNo()));
        no2.setText(String.valueOf(AQD.getNo2()));
        o3.setText(String.valueOf(AQD.getO3()));
        so2.setText(String.valueOf(AQD.getSo2()));
        pm2.setText(String.valueOf(AQD.getPm2()));
        pm10.setText(String.valueOf(AQD.getPm10()));
        nh3.setText(String.valueOf(AQD.getNh3()));
    }
    public void setWeatherbyDB(Database db){
        temp.setText(String.valueOf(db.getTemperature()));
        main.setText(db.getMain());
        descrip.setText(db.getDescription());
        wind.setText(String.valueOf(db.getWind()));
        humid.setText(String.valueOf(db.getHumidity()));
        press.setText(String.valueOf(db.getPressure()));
        visib.setText(String.valueOf(db.getVisibility()));
        feels_like.setText(String.valueOf(db.getFeelsLike()));
        min_temp.setText(String.valueOf(db.getMinTemperature()));
        max_temp.setText(String.valueOf(db.getMaxTemperature()));
        srt.setText(db.getSunrise());
        sst.setText(db.getSunset());
    }
    public void setAirQualbyDB(Database db){
        aqi.setText(String.valueOf(db.getAqi()));
        co.setText(String.valueOf(db.getCo()));
        no.setText(String.valueOf(db.getNo()));
        no2.setText(String.valueOf(db.getNo2()));
        o3.setText(String.valueOf(db.getO3()));
        so2.setText(String.valueOf(db.getSo2()));
        pm2.setText(String.valueOf(db.getPm2()));
        pm10.setText(String.valueOf(db.getPm10()));
        nh3.setText(String.valueOf(db.getNh3()));
    }
    public void setForecastbyDB(Database db){
        String foreCastArray[][] = db.getForecast();
        date1.setText(String.valueOf(foreCastArray[0][0]));
        max1.setText(String.valueOf(foreCastArray[0][1]));
        min1.setText(String.valueOf(foreCastArray[0][2]));
        main1.setText(String.valueOf(foreCastArray[0][3]));

        date2.setText(String.valueOf(foreCastArray[1][0]));
        max2.setText(String.valueOf(foreCastArray[1][1]));
        min2.setText(String.valueOf(foreCastArray[1][2]));
        main2.setText(String.valueOf(foreCastArray[1][3]));

        date3.setText(String.valueOf(foreCastArray[2][0]));
        max3.setText(String.valueOf(foreCastArray[2][1]));
        min3.setText(String.valueOf(foreCastArray[2][2]));
        main3.setText(String.valueOf(foreCastArray[2][3]));

        date4.setText(String.valueOf(foreCastArray[3][0]));
        max4.setText(String.valueOf(foreCastArray[3][1]));
        min4.setText(String.valueOf(foreCastArray[3][2]));
        main4.setText(String.valueOf(foreCastArray[3][3]));

        date5.setText(String.valueOf(foreCastArray[4][0]));
        max5.setText(String.valueOf(foreCastArray[4][1]));
        min5.setText(String.valueOf(foreCastArray[4][2]));
        main5.setText(String.valueOf(foreCastArray[4][3]));
    }
    public void initialize() {
        db.connect();
        String weather;
        WeatherData Curr_Weather = new WeatherData();
        ForecastData ForeCast = new ForecastData();
        AirQualityData AQD = new AirQualityData();
        WeatherNotification notifications = new WeatherNotification();
        myChoiceBox.getItems().addAll(search);
        myChoiceBox.setValue("Search By City");
        city_name.setText("Lahore");
        Curr_Weather.fetchWeatherData("Lahore");
        ForeCast.fetchForecast("Lahore");
        AQD.fetchAirPollutionData("Lahore");
        if (db.recordExist("Lahore")){
            Database data = new Database();
            data.fetchData("Lahore");
            setWeatherbyDB(db);
            setAirQualbyDB(db);
            setForecastbyDB(db);
        }
        else {
            db.storeWeatherDb(Curr_Weather);
            db.storeAirDb(AQD);
            db.storeForecastDb(ForeCast);
        }
        setCurrWeather(Curr_Weather);
        setForeCast(ForeCast);
        setAirQuality(AQD);
        notification = notifications.getNotifications("Lahore");
        if (notification[0]!=null || notification[1]!=null) {
            notif.setText(notification[0] + "\n" + notification[1]);
        }
        else
            notif_dot.setVisible(false);
        weather = Curr_Weather.getMain();
        backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        image = setBackground(weather);
        backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        background = new Background(backgroundImage);
        app.setBackground(background);
        notif_pane.setVisible(false);
    }
    public void notification(ActionEvent actionEvent){
        notif_dot.setVisible(false);
        if (notif_pane.isVisible())
            notif_pane.setVisible(false);
        else
            notif_pane.setVisible(true);
    }
    public void changeSearch(ActionEvent actionEvent){
        String choice = myChoiceBox.getValue();
        if ("Search By City".equals(choice)){
            longit.setVisible(false);
            latit.setVisible(false);
            city.setVisible(true);
        }
        else {
            longit.setVisible(true);
            latit.setVisible(true);
            city.setVisible(false);
        }
    }
    public void Search() {
        if (!"".equals(city.getText())) {
            String searched_city;
            String weather;
            WeatherData Curr_Weather = new WeatherData();
            ForecastData ForeCast = new ForecastData();
            AirQualityData AQD = new AirQualityData();
            searched_city = city.getText();
            city_name.setText(searched_city);
            if (db.recordExist(searched_city)){
                db.fetchData(searched_city);
            }
            else {
                Curr_Weather.fetchWeatherData(searched_city);
                ForeCast.fetchForecast(searched_city);
                AQD.fetchAirPollutionData(searched_city);
                db.storeWeatherDb(Curr_Weather);
                db.storeAirDb(AQD);
                db.storeForecastDb(ForeCast);
            }
            setCurrWeather(Curr_Weather);
            setForeCast(ForeCast);
            setAirQuality(AQD);
            weather = Curr_Weather.getMain();
            backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
            image = setBackground(weather);
            backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            background = new Background(backgroundImage);
            app.setBackground(background);
            city.setText("");
        }
        else{
            String city;
            String weather;
            Double lon = Double.parseDouble(longit.getText());
            Double lat =  Double.parseDouble(latit.getText());
            WeatherData Curr_Weather = new WeatherData();
            ForecastData ForeCast = new ForecastData();
            AirQualityData AQD = new AirQualityData();
            Curr_Weather.fetchWeatherData(lon, lat);
            ForeCast.fetchForecast(lon, lat);
            AQD.fetchAirPollutionData(lon, lat);
            db.storeWeatherDb(Curr_Weather);
            db.storeAirDb(AQD);
            db.storeForecastDb(ForeCast);
            city_name.setText(Curr_Weather.getCity());
            setCurrWeather(Curr_Weather);
            setForeCast(ForeCast);
            setAirQuality(AQD);
            weather = Curr_Weather.getMain();
            backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
            image = setBackground(weather);
            backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            background = new Background(backgroundImage);
            app.setBackground(background);
            longit.setText("");
            latit.setText("");
        }
    }
}