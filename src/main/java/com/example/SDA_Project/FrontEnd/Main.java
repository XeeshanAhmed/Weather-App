package com.example.SDA_Project.FrontEnd;

import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        boolean run=true;
        int choice;
        String city = null;
        double longitude = 0,latitude = 0;
        Weather weather=new Weather();
        AirQuality airQuality=new AirQuality();
        Forecast forecast=new Forecast();
        Scanner scanner=new Scanner(System.in);
        logo();
        System.out.println("\t\t\t\t\t\t\t\t\t1-Search location by City/Country name.");
        System.out.println("\t\t\t\t\t\t\t\t\t2-Search location by Coordinates.");
        System.out.println("\t\t\t\t\t\t\t\t\t3-Exit");
        while(true)
        {
            try {
                System.out.print("\t\t\t\t\t\t\t\t\tYour Choice : ");
                choice = scanner.nextInt();
                if (choice == 1) {
                    city = InputLocation();
                    weather.SetLocation(city);
                    weather.getCurrentWeather();
                    weather.getBasicInformation();
                    weather.getSunInfo();
                    airQuality.getAirPollutionData(city);
                    forecast.setLocation(city);
                    forecast.setForecastData();
                    break;
                } else if (choice == 2) {
                    double[] coordinates = new double[2];
                    coordinates = inputLocation();
                    longitude = coordinates[0];
                    latitude = coordinates[1];
                    weather.SetLocation(longitude, latitude);
                    weather.getCurrentWeather();
                    weather.getBasicInformation();
                    weather.getSunInfo();
                    airQuality.getAirPollutionData(city);//yahan city ko change kr k coordinates dene hein yad se
                    forecast.setLocation(longitude, latitude);
                    forecast.setForecastData();
                    break;
                } else if (choice == 3) {
                    System.out.println("Exitig....");
                    System.exit(1);
                } else {
                    System.out.println("Invalid Input");
                }
            }catch (InputMismatchException e)
            {
                System.out.println("Invalid Input");
                scanner.nextLine();
            }
        }
        while (run) {

            try {
                Thread.sleep(2222);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            logo();
            System.out.println("\t\t\t\t\t\t\t\t\t1-Current Weather Conditions");
            System.out.println("\t\t\t\t\t\t\t\t\t2-Basic Information");
            System.out.println("\t\t\t\t\t\t\t\t\t3-Sunset and Sunrise Time");
            System.out.println("\t\t\t\t\t\t\t\t\t4-Weather Forecast");
            System.out.println("\t\t\t\t\t\t\t\t\t5-Air Pollution Data");
            System.out.println("\t\t\t\t\t\t\t\t\t6- Polluting Gasses");
            System.out.println("\t\t\t\t\t\t\t\t\t7- Change Location");
            System.out.println("\t\t\t\t\t\t\t\t\t8-Exit");
            System.out.print("\t\t\t\t\t\t\t\t\tSelect Option : ");
            try {
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        weather.showCurrentWeatherConditions();
                        break;

                    case 2:
                        weather.showBasicInformation();
                        break;

                    case 3:
                        weather.showSunSetRiseInfo();
                        break;

                    case 4:
                        forecast.showForecast();
                        break;

                    case 5:
                        airQuality.showAirPollutionData();
                        break;

                    case 6:
                        airQuality.showGassesData();
                        break;

                    case 7:
                        System.out.println("\t\t\t\t\t\t\t\t\t1-Search location by City/Country name.");
                        System.out.println("\t\t\t\t\t\t\t\t\t2-Search location by Coordinates.");
                        System.out.println("\t\t\t\t\t\t\t\t\t3-Exit.");
                        while (true) {
                            try {
                                System.out.print("\t\t\t\t\t\t\t\t\tYour Choice : ");
                                choice = scanner.nextInt();
                                if (choice == 1) {
                                    city = InputLocation();
                                    weather.SetLocation(city);
                                    weather.getCurrentWeather();
                                    weather.getBasicInformation();
                                    weather.getSunInfo();
                                    airQuality.getAirPollutionData(city);
                                    forecast.setLocation(city);
                                    forecast.setForecastData();
                                    break;
                                } else if (choice == 2) {
                                    double[] coordinates = new double[2];
                                    coordinates = inputLocation();
                                    longitude = coordinates[0];
                                    latitude = coordinates[1];
                                    weather.SetLocation(longitude, latitude);
                                    weather.getCurrentWeather();
                                    weather.getBasicInformation();
                                    weather.getSunInfo();
                                    airQuality.getAirPollutionData(city);//yahan city ko change kr k coordinates dene hein yad se
                                    forecast.setLocation(longitude, latitude);
                                    forecast.setForecastData();
                                    break;
                                } else if (choice == 3) {
                                    System.out.println("Exitig....");
                                    System.exit(1);
                                } else {
                                    System.out.println("Invalid Input");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid Input");
                                scanner.nextLine();
                            }
                        }
                        break;

                    case 8:
                        run = false;
                        break;

                    default:
                        System.out.println("Invalid Input");
                        break;
                }
            }catch(InputMismatchException e)
            {
                System.out.println("Invalid Input");
                scanner.nextLine();
            }
        }




    }
    public static void logo()
    {
        System.out.println("\t\t\t\t\t\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t\t\t\t\t\t\t                                    Weather Application                                  ");
        System.out.println("\t\t\t\t\t\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    };
    public static String InputLocation()
    {
        System.out.print("City name : ");
        Scanner scanner=new Scanner(System.in);
        return scanner.next();
    };
    public static double[] inputLocation()
    {
        Scanner scanner=new Scanner(System.in);
        double longitude,latitude;
        System.out.print("Enter longitude : ");
        longitude=scanner.nextDouble();
        while(longitude<-180 || longitude>180)
        {
            System.out.print("Longitude should be between -180 and 180 degrees : ");
            longitude=scanner.nextDouble();
        }
        System.out.print("Enter latitude : ");
        latitude=scanner.nextDouble();
        while(latitude<-90 || latitude>90)
        {
            System.out.print("Latitude should be between -90 and 90 degrees : ");
            latitude=scanner.nextDouble();
        }
        double[] coordinates=new double[2];
        coordinates[0]=longitude;
        coordinates[1]=latitude;
        return coordinates;
    };
    public static void setUserChoice()
    {

    }
}