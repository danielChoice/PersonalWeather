package com.example.personalweather.adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalweather.R;
import com.example.personalweather.pojo.WeatherOnNextDays;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class WeatherBlockAdapter extends RecyclerView.Adapter<WeatherBlockAdapter.weatherBlockViewHolder> {



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
    public weatherBlockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.today_block, parent, false);
        return new weatherBlockViewHolder(view);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull weatherBlockViewHolder holder, int position) {



        if(weatherOnNextDays != null) {
            Calendar calendar = Calendar.getInstance();
            long time = weatherOnNextDays.getHourDay0(0);
            calendar.setTimeInMillis(time*1000L);

            Date date = new Date();
            date.setTime(System.currentTimeMillis());
            Date calendarDate = new Date(calendar.getTimeInMillis());

            int hoursFromPhone = date.getHours();
            int hoursFromServer = calendarDate.getHours();



            if(hoursFromPhone >= hoursFromServer & hoursFromPhone < 15) {
                hoursFromServer = (hoursFromPhone - hoursFromServer) + (hoursFromServer + position + 1);

                holder.textViewTemperature.setText(changeTemp(weatherOnNextDays.getTempDay0(hoursFromServer)) + "");
                Date dateOfWeather = new Date(weatherOnNextDays.getHourDay0(hoursFromServer)*1000L);
                holder.textTime.setText(String.format("%02d:%02d", dateOfWeather.getHours(), dateOfWeather.getMinutes()));
                setImg(holder, weatherOnNextDays.getWeatherStatusDay0(hoursFromServer));



            }else if (hoursFromPhone >= 16){
                hoursFromServer = 17 + position;
                holder.textViewTemperature.setText(changeTemp(weatherOnNextDays.getTempDay0(hoursFromServer)) + "");
                Date dateOfWeather = new Date(weatherOnNextDays.getHourDay0(hoursFromServer)*1000L);
                holder.textTime.setText(String.format("%02d:%02d", dateOfWeather.getHours(), dateOfWeather.getMinutes()));
                setImg(holder, weatherOnNextDays.getWeatherStatusDay0(hoursFromServer));



            }else{
                hoursFromServer = hoursFromPhone + position;
                holder.textViewTemperature.setText(changeTemp(weatherOnNextDays.getTempDay0(hoursFromServer)) + "");
                Date dateOfWeather = new Date(weatherOnNextDays.getHourDay0(hoursFromServer)*1000L);
                holder.textTime.setText(String.format("%02d:%02d", dateOfWeather.getHours(), dateOfWeather.getMinutes()));
                setImg(holder, weatherOnNextDays.getWeatherStatusDay0(hoursFromServer));
            }






        }





    }

    public String changeTemp(String dayAvgTemp) {
        int dayAvgTempInt = Integer.parseInt(dayAvgTemp);
        if (dayAvgTempInt > 0) {
            return "+ " + dayAvgTemp;
        } else return dayAvgTemp;
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class weatherBlockViewHolder extends RecyclerView.ViewHolder {
        TextView textTime;
        TextView textViewTemperature;
        ImageView imageView;
        public weatherBlockViewHolder(@NonNull View itemView) {
            super(itemView);
            textTime = itemView.findViewById(R.id.textViewTimeOfWeather);
            textViewTemperature = itemView.findViewById(R.id.TextViewTempBlock);
            imageView = itemView.findViewById(R.id.imageStatusOfWeather);
        }
    }

    public void setImg(WeatherBlockAdapter.weatherBlockViewHolder holder, String condition) {
        switch (condition) {
            case "overcast":
                condition = "Пасмурно";
                holder.imageView.setImageResource(R.drawable.cloudness);
                break;
            case "clear":
                condition = "Ясно";
                holder.imageView.setImageResource(R.drawable.cloud_sun);
                break;
            case "partly-cloudy":
                condition = "Малооблачно";
                holder.imageView.setImageResource(R.drawable.cloud_sun);
                break;
            case "cloudy":
                condition = "Облачно с прояснениями";
                holder.imageView.setImageResource(R.drawable.cloud_sun);
                break;
            case "drizzle":
                condition = "Морось";
                holder.imageView.setImageResource(R.drawable.rain);
                break;
            case "light-rain":
                condition = "Небольшой дождь";
                holder.imageView.setImageResource(R.drawable.rain);
                break;
            case "rain":
                condition = "Дождь";
                holder.imageView.setImageResource(R.drawable.rain);
                break;
            case "moderate-rain":
                condition = "Умеренный дождь";
                holder.imageView.setImageResource(R.drawable.rain);
                break;
            case "heavy-rain":
                condition = "Сильный дождь";
                holder.imageView.setImageResource(R.drawable.rain);
                break;
            case "continuous-heavy-rain":
                condition = "Длительный сильный дождь";
                holder.imageView.setImageResource(R.drawable.rain);
                break;
            case "showers":
                condition = "Ливень";
                holder.imageView.setImageResource(R.drawable.rain);
                break;
            case "wet-snow":
                condition = "Дождь со снегом";
                holder.imageView.setImageResource(R.drawable.snow);
                break;
            case "light-snow":
                condition = "Небольшой снег";
                holder.imageView.setImageResource(R.drawable.snow);
                break;

            case "snow":
                condition = "Снег";
                holder.imageView.setImageResource(R.drawable.snow);
                break;

            case "snow-showers":
                condition = "Снегопад";
                holder.imageView.setImageResource(R.drawable.snow);
                break;

            case "hail":
                condition = "Град";
                holder.imageView.setImageResource(R.drawable.snow);
                break;
            case "thunderstorm":
                condition = "Гроза";
                holder.imageView.setImageResource(R.drawable.thunder);
                break;

            case "thunderstorm-with-rain":
                condition = "Дождь с грозой";
                holder.imageView.setImageResource(R.drawable.thunder);
                break;

            case "thunderstorm-with-hail":
                condition = "Гроза с градом";
                holder.imageView.setImageResource(R.drawable.thunder);
                break;
            default:
                condition = "Нет данных";
        }
    }


}