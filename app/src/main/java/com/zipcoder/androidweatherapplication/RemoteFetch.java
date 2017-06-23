package com.zipcoder.androidweatherapplication;


import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vijaysingh on 08/02/16.
 */
public class RemoteFetch {


    private static final String CURRENT = Helper.CURRENT_WEATHER_ADI_URL + Helper.setUnits("imperial") + Helper.API_ID;

    public static JSONObject getCurrentWeatherJSON(String city){
        try{
            URL url = new URL(String.format(CURRENT, city));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp = "";
            while (((tmp=reader.readLine())!=null))
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            // check weather request is successfull or not
            if (data.getInt("cod") != 200){
                return null;
            }

            return data;

        } catch (Exception e) {
            return null;
        }
    }
}
