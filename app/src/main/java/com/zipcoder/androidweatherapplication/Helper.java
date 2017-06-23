package com.zipcoder.androidweatherapplication;

import java.util.Locale;

/**
 * Created by iyasuwatts on 6/21/17.
 */

public class Helper {
    public static final String CURRENT_WEATHER_ADI_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s";

    public static final String FIVE_DAY_WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";

    public static final String API_ID = "&appid="+"619e067837104bed5aba0784c43eaa5d";

    public static String setUnits(String units) {
        return "&units=" + units.toLowerCase(Locale.US);
    }


}
