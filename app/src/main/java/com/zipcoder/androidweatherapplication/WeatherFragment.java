package com.zipcoder.androidweatherapplication;


import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
    SearchView searchView;

    ListView listView;
    ArrayList<WeatherModel> weatherModels;
    private static CustomListAdapter adapter;

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

        weatherModels = new ArrayList<>();
        adapter = new CustomListAdapter(weatherModels, getActivity());

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        cityField = (TextView) rootView.findViewById(R.id.city_field);
        updatedField = (TextView) rootView.findViewById(R.id.update_field);
        detailsField = (TextView) rootView.findViewById(R.id.details_filed);
        currentTempField = (TextView) rootView.findViewById(R.id.current_temp_field);
        weatherIcon = (TextView) rootView.findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);
        listView = (ListView) rootView.findViewById(R.id.list);


        // Search View
        searchView=(SearchView) rootView.findViewById(R.id.searchView);
        searchView.setQueryHint("Search View");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                changeCity(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        listView.setAdapter(adapter);
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
                            renderFiveDayWeather(fiveDay);
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


    private void renderFiveDayWeather(JSONObject json){
        adapter.clear();
        WeatherModel weatherModel;
        try {

            int length  = json.getJSONArray("list").length();

            for (int i = 0; i < length; i++) {
                weatherModel = JSONToModel.forecastToModel(json.getJSONArray("list").getJSONObject(i));
                adapter.add(weatherModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


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

    public void changeCity(String city){
        updateWeatherData(city);
    }

    @Override
    public void onClick(View v) {

    }
}
