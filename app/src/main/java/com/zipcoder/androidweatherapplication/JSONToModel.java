package com.zipcoder.androidweatherapplication;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by iyasuwatts on 6/22/17.
 */

public class JSONToModel {




    public static WeatherModel currentToModel(JSONObject jsonObject){
        WeatherModel weatherModel = new WeatherModel();
        DateFormat df = DateFormat.getDateInstance();
        try {
            JSONObject details = jsonObject.getJSONArray("weather").getJSONObject(0);
            JSONObject main = jsonObject.getJSONObject("main");
            JSONObject sys = jsonObject.getJSONObject("sys");

            weatherModel.setDescription(details.getString("description"));
            weatherModel.setLastUpdate(df.format(new Date(jsonObject.getLong("dt")*1000)));
            weatherModel.setTemp(main.getString("temp"));
            weatherModel.setTempHigh(main.getString("temp_max"));
            weatherModel.setTempLow(main.getString("temp_min"));
            weatherModel.setHumidity(main.getString("humidity"));
            weatherModel.setCity(jsonObject.getString("name"));
            weatherModel.setCountry(sys.getString("country"));
            weatherModel.setPressure(main.getString("pressure"));
            weatherModel.setWeatherIconId(details.getInt("id"));
            weatherModel.setSunrise(sys.getLong("sunrise")*1000);
            weatherModel.setSunset(sys.getLong("sunset")*1000);

        }catch (JSONException e) {
            e.printStackTrace();
        }

        return weatherModel;
    }

    public static WeatherModel forecastToModel(JSONObject jsonObject){
        Log.i("sd",(String.valueOf(jsonObject)));


        WeatherModel weatherModel = new WeatherModel();
        try {
            JSONObject details = jsonObject.getJSONArray("weather").getJSONObject(0);
            JSONObject main = jsonObject.getJSONObject("main");

            weatherModel.setDescription(details.getString("description"));
            weatherModel.setTemp(main.getString("temp"));
            weatherModel.setHumidity(main.getString("humidity"));
            weatherModel.setTime(jsonObject.getString("dt_txt"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return weatherModel;
    }

}
