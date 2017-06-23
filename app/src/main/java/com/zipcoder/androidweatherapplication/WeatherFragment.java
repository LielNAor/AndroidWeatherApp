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
    ListView listView;
    SearchView searchView;

    String selectedCity = "wilmington";

    Handler handler;


    public WeatherFragment() {
        // Required empty public constructor
        handler = new Handler();

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        return rootView;
    }


    @Override
    public void onClick(View v) {

    }
}
