package com.zipcoder.androidweatherapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        update();
    }

    private void update(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.weather, new WeatherFragment())
                .commit();
    }
}
