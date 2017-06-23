package com.zipcoder.androidweatherapplication;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment implements View.OnClickListener {


    String selectedCity = "philalphia";
    Handler handler;


    public WeatherFragment() {
        // Required empty public constructor
        handler = new Handler();

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateWeatherData(selectedCity);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        return rootView;
    }


    private void updateWeatherData(final String city){
        new Thread() {
            public void run() {
                final JSONObject current = RemoteFetch.getCurrentWeatherJSON(city);

                handler.post(new Runnable() {
                    public void run() {
                        if (current == null){
                            Toast toast = Toast.makeText(getContext(), getResources().getString(R.string.place_not_found), Toast.LENGTH_SHORT);
                            toast.show();
                        }else {
                            Log.i("Current Weather Data ", String.valueOf(current));
                            Log.i("Weather Model", String.valueOf(JSONToModel.currentToModel(current)));

                        }


                    }
                });

            }

        }.start();
    }

    @Override
    public void onClick(View v) {

    }
}
