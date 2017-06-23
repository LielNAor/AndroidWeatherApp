package com.zipcoder.androidweatherapplication;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment implements View.OnClickListener {


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
