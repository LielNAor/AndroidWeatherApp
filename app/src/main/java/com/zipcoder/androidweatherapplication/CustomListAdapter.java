package com.zipcoder.androidweatherapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by iyasuwatts on 6/21/17.
 */

public class CustomListAdapter extends ArrayAdapter<WeatherModel> {


    public static class ViewHolder {
        TextView details;
        TextView weatherIcon;
        TextView temp;
        TextView time;

    }
    public CustomListAdapter(ArrayList<WeatherModel> data, Context context) {
        super(context, R.layout.list_item, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        WeatherModel weatherModel = getItem(position);

        ViewHolder viewHolder;


        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.details = (TextView) convertView.findViewById(R.id.details_field);
            viewHolder.temp = (TextView) convertView.findViewById(R.id.current_temp_field);
            viewHolder.weatherIcon = (TextView) convertView.findViewById(R.id.weather_icon);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.details.setText(weatherModel.description);
        viewHolder.temp.setText(weatherModel.temp);
        viewHolder.time.setText(weatherModel.time);

        return convertView;
    }

}
