package com.example.personalweather.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalweather.R;
import com.example.personalweather.pojo.WeatherOnNextDays;

public class WeatherNextDayAdapter extends RecyclerView.Adapter<WeatherNextDayAdapter.NextDayViewHolder> {

 private WeatherOnNextDays weatherOnNextDays;

    public WeatherOnNextDays getWeatherOnNextDays() {
        return weatherOnNextDays;
    }

    public void setWeatherOnNextDays(WeatherOnNextDays weatherOnNextDays) {
        this.weatherOnNextDays = weatherOnNextDays;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NextDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_next_days, parent, false);
        return new NextDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NextDayViewHolder holder, int position) {
        if(weatherOnNextDays != null){
            switch (position){
                case 0:
                    holder.textViewDate.setText(weatherOnNextDays.getDay2());
                    break;
                    case 1:
                    holder.textViewDate.setText(weatherOnNextDays.getDay3());
                    break;
                    case 2:
                    holder.textViewDate.setText(weatherOnNextDays.getDay4());
                    break;
                    case 3:
                    holder.textViewDate.setText(weatherOnNextDays.getDay5());
                    break;
                    case 4:
                    holder.textViewDate.setText(weatherOnNextDays.getDay6());
                    break;
                    case 5:
                    holder.textViewDate.setText(weatherOnNextDays.getDay7());
                    break;


            }
        }

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class NextDayViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDate;
        TextView textViewDayOfWeek;
        TextView textViewTemperature;
        public NextDayViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewDayOfWeek = itemView.findViewById(R.id.textViewDayOfWeek);
            textViewTemperature = itemView.findViewById(R.id.textViewTemperature);
        }
    }
}
