package com.zipcoder.androidweatherapplication;


import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment implements View.OnClickListener {

    TextView cityField;
    TextView updatedField;
    TextView detailsField;
    TextView currentTempField;
    TextView weatherIcon;
    Typeface weatherFont;

    String selectedCity = "philalphia";
    Handler handler;


    public WeatherFragment() {
        // Required empty public constructor
        handler = new Handler();

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weatherfont.ttf");
        updateWeatherData(selectedCity);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        cityField = (TextView) rootView.findViewById(R.id.city_field);
        updatedField = (TextView) rootView.findViewById(R.id.update_field);
        detailsField = (TextView) rootView.findViewById(R.id.details_filed);
        currentTempField = (TextView) rootView.findViewById(R.id.current_temp_field);
        weatherIcon = (TextView) rootView.findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);
        return rootView;
    }


    private void updateWeatherData(final String city){
        new Thread() {
            public void run() {
                final JSONObject current = RemoteFetch.getCurrentWeatherJSON(city);
                final JSONObject fiveDay = RemoteFetch.getFiveDayWeatherJSON(city);
                handler.post(new Runnable() {
                    public void run() {
                        if (current == null){
                            Toast toast = Toast.makeText(getContext(), getResources().getString(R.string.place_not_found), Toast.LENGTH_SHORT);
                            toast.show();
                        }else {
                            renderCurrentWeather(current);
                            Log.i("Five Day", String.valueOf(fiveDay));
                        }
                    }
                });

            }

        }.start();
    }

    private void renderCurrentWeather(JSONObject json){


        WeatherModel weatherModelCurrent = JSONToModel.currentToModel(json);

        updatedField.setText("Last updated: " + weatherModelCurrent.getLastUpdate());
        cityField.setText(weatherModelCurrent.getCity() + ", " + weatherModelCurrent.getCountry());
        currentTempField.setText(weatherModelCurrent.getTemp());
        detailsField.setText(weatherModelCurrent.getDescription().toUpperCase(Locale.US) +
                "\n" + "Humidity: " + weatherModelCurrent.getHumidity() +
                "\n" + "Pressure:" + weatherModelCurrent.getPressure());
        setWeatherIcon(weatherModelCurrent.getWeatherIconId(), weatherModelCurrent.getSunrise(), weatherModelCurrent.getSunset());

    }

    private void setWeatherIcon(int actualId, long sunrise, long sunset){
        int id = actualId/100;
        String icon = "";
        if (actualId == 800){
            long currentTime = new Date().getTime();
            if (currentTime >= sunrise && currentTime<sunset){
                icon = getActivity().getString(R.string.weather_sunny);
            } else {
                icon = getActivity().getString(R.string.weather_clear_night);
            }
        } else {
            switch (id){
                case 2:
                    icon = getActivity().getString(R.string.weather_thunder);
                    break;
                case 3:
                    icon = getActivity().getString(R.string.weather_drizzle);
                    break;
                case 7:
                    icon = getActivity().getString(R.string.weather_foggy);
                    break;
                case 8:
                    icon = getActivity().getString(R.string.weather_cloudy);
                    break;
                case 6 : icon = getActivity().getString(R.string.weather_snowy);
                    break;
                case 5 : icon = getActivity().getString(R.string.weather_rainy);
                    break;

            }
        }
        weatherIcon.setText(icon);
    }

    @Override
    public void onClick(View v) {

    }
}
