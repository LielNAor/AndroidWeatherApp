package com.zipcoder.androidweatherapplication;


/**
 * Created by iyasuwatts on 6/21/17.
 */

public class WeatherModel {



    String temp;
    String tempHigh;
    String tempLow;
    String humidity;
    String pressure;
    String city;
    String country;
    String description;
    String lastUpdate;
    String time;
    int weatherIconId;
    long sunrise;
    long sunset;




    public WeatherModel(){

    }



    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setTempHigh(String tempHigh) {
        this.tempHigh = tempHigh;
    }

    public void setTempLow(String tempLow) {
        this.tempLow = tempLow;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setWeatherIconId(int weatherIconId) {
        this.weatherIconId = weatherIconId;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getPressure() {
        return pressure + "hPa";
    }

    public String getCity() {
        return city;
    }

    public String getHumidity() {
        return humidity + "%";
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public String getTemp() {
        return temp + "F";
    }

    public int getWeatherIconId() {
        return weatherIconId;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getTempHigh() {
        return tempHigh;
    }

    public String getTempLow() {
        return tempLow;
    }
}
