package com.example.personalweather.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalweather.R;
import com.example.personalweather.pojo.WeatherOnNextDays;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

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
                    Date date = weatherOnNextDays.getDay2();
                    SimpleDateFormat newDate = new SimpleDateFormat("dd MMMM", Locale.getDefault());
                    String result = newDate.format(date);

                    JsonArray jsonArray =  weatherOnNextDays.getForecasts();
                    JsonObject jsonObject = (JsonObject) jsonArray.get(1);
                    JsonObject parts = (JsonObject) jsonObject.get("parts");
                    JsonObject day = (JsonObject) parts.get("day");
                    String dayAvgTemp = day.get("temp_avg").toString();
                    holder.textViewDate.setText(result);
                    holder.textViewDayOfWeek.setText(R.string.tomorrow);
                    holder.textViewTemperature.setText(changeTemp(dayAvgTemp));



//                    Log.i("MAX", jsonObjectParts  + "");
                    break;
                case 1:
                        date = weatherOnNextDays.getDay3();
                        Calendar calendar = new GregorianCalendar();
                        calendar.setTime(date);
                        newDate = new SimpleDateFormat("dd MMMM", Locale.getDefault());
                        result = newDate.format(date);
                        holder.textViewDate.setText(result);
                        calendar.setTime(date);
                        int dayOfWeek = date.getDay();
                    jsonArray =  weatherOnNextDays.getForecasts();
                     jsonObject = (JsonObject) jsonArray.get(2);
                    parts = (JsonObject) jsonObject.get("parts");
                     day = (JsonObject) parts.get("day");
                   dayAvgTemp = day.get("temp_avg").toString();


                        holder.textViewDayOfWeek.setText(getDayOfWeek(dayOfWeek));
                    holder.textViewTemperature.setText(changeTemp(dayAvgTemp));

                    break;
                    case 2:
                        date = weatherOnNextDays.getDay4();
                        newDate = new SimpleDateFormat("dd MMMM", Locale.getDefault());
                        result = newDate.format(date);
                        holder.textViewDate.setText(result);
                        dayOfWeek = date.getDay();
                        jsonArray =  weatherOnNextDays.getForecasts();
                        jsonObject = (JsonObject) jsonArray.get(3);
                        parts = (JsonObject) jsonObject.get("parts");
                        day = (JsonObject) parts.get("day");
                        dayAvgTemp = day.get("temp_avg").toString();

                        holder.textViewTemperature.setText(changeTemp(dayAvgTemp));

                        holder.textViewDayOfWeek.setText(getDayOfWeek(dayOfWeek));
                    break;
                    case 3:
                        date = weatherOnNextDays.getDay5();

                        newDate = new SimpleDateFormat("dd MMMM", Locale.getDefault());
                        result = newDate.format(date);
                        holder.textViewDate.setText(result);
                        dayOfWeek = date.getDay();
                        jsonArray =  weatherOnNextDays.getForecasts();
                        jsonObject = (JsonObject) jsonArray.get(4);
                        parts = (JsonObject) jsonObject.get("parts");
                        day = (JsonObject) parts.get("day");
                        dayAvgTemp = day.get("temp_avg").toString();


                        holder.textViewTemperature.setText(changeTemp(dayAvgTemp));
                        holder.textViewDayOfWeek.setText(getDayOfWeek(dayOfWeek));
                    break;
                    case 4:
                        date = weatherOnNextDays.getDay6();
                        newDate = new SimpleDateFormat("dd MMMM", Locale.getDefault());
                        result = newDate.format(date);
                        holder.textViewDate.setText(result);
                        dayOfWeek = date.getDay();
                        Log.i("DAYY", dayOfWeek + "");
                        jsonArray =  weatherOnNextDays.getForecasts();
                        jsonObject = (JsonObject) jsonArray.get(5);
                        parts = (JsonObject) jsonObject.get("parts");
                        day = (JsonObject) parts.get("day");
                        dayAvgTemp = day.get("temp_avg").toString();
                        holder.textViewTemperature.setText(changeTemp(dayAvgTemp));
                        holder.textViewDayOfWeek.setText(getDayOfWeek(dayOfWeek));
                    break;
                    case 5:
                        date = weatherOnNextDays.getDay7();
                        newDate = new SimpleDateFormat("dd MMMM", Locale.getDefault());
                        result = newDate.format(date);
                        holder.textViewDate.setText(result);
                        dayOfWeek = date.getDay();
                        jsonArray =  weatherOnNextDays.getForecasts();
                        jsonObject = (JsonObject) jsonArray.get(6);
                        parts = (JsonObject) jsonObject.get("parts");
                        day = (JsonObject) parts.get("day");
                        dayAvgTemp = day.get("temp_avg").toString();

                        holder.textViewTemperature.setText(changeTemp(dayAvgTemp));
                        holder.textViewDayOfWeek.setText(getDayOfWeek(dayOfWeek));
                    break;


            }
        }

    }

    public String changeTemp(String dayAvgTemp ){
        int dayAvgTempInt = Integer.parseInt(dayAvgTemp);
        if(dayAvgTempInt > 0){
            return "+ " + dayAvgTemp;
        }
        else return dayAvgTemp;
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

    public String getDayOfWeek(int dayOfWeek){
        String day = "";
        if (dayOfWeek == 1){
            day = "Понедельник";
        }else if(dayOfWeek == 2){
            day = "Вторник";
        }
        else if(dayOfWeek == 3){
            day = "Среда";
        }
        else if(dayOfWeek == 4){
            day = "Четверг";
        }
        else if(dayOfWeek == 5){
            day = "Пятница";
        }
        else if(dayOfWeek == 6){
            day = "Суббота";
        }
        else if (dayOfWeek == 0){
            day = "Воскресенье";
        }
        return day;
    }
}
