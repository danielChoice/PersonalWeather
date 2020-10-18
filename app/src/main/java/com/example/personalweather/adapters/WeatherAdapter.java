package com.example.personalweather.adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalweather.City;
import com.example.personalweather.R;
import com.example.personalweather.pojo.ResponseNow;
import com.google.gson.JsonObject;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private ResponseNow response = null;
    private String city;


    public String getCity() {
        return city;
    }



    public void setCity(String city) {
        this.city = city;
        notifyDataSetChanged();
    }

    public ResponseNow getResponse() {
        return response;
    }

    public void setResponse(ResponseNow response) {
        this.response = response;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new WeatherViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        String factTemp = "";

        if (response != null) {
            long time = response.getNow();


            JsonObject weatherFact = response.getFact();
            int tempInt = Integer.parseInt(weatherFact.get("temp").toString());
            if (tempInt > 0) {
                factTemp = "+" + tempInt;

            } else {
                factTemp = "" + tempInt;
            }

            String wind = weatherFact.get("wind_speed").toString() + " м/с";
            String precipitation = weatherFact.get("condition").toString();
            precipitation = precipitation.replace("\"", "");

            switch (precipitation) {
                case "overcast":
                    precipitation = "Пасмурно";
                    break;
                case "clear":
                    precipitation = "Ясно";
                    break;
                case "partly-cloudy":
                    precipitation = "Малооблачно";
                    break;
                case "cloudy":
                    precipitation = "Облачно с прояснениями";
                    break;
                case "drizzle":
                    precipitation = "Морось";
                    break;
                case "light-rain":
                    precipitation = "Небольшой дождь";
                    break;
                case "rain":
                    precipitation = "Дождь";
                    break;
                case "moderate-rain":
                    precipitation = "Умеренный дождь";
                    break;
                case "heavy-rain":
                    precipitation = "Сильный дождь";
                    break;
                case "continuous-heavy-rain":
                    precipitation = "Длительный сильный дождь";
                    break;
                case "showers":
                    precipitation = "Ливень";
                    break;
                case "wet-snow":
                    precipitation = "Дождь со снегом";
                    break;
                case "light-snow":
                    precipitation = "Небольшой снег";
                    break;

                case "snow":
                    precipitation = "Снег";
                    break;

                case "snow-showers":
                    precipitation = "Снегопад";
                    break;

                case "hail":
                    precipitation = "Град";
                    break;
                case "thunderstorm":
                    precipitation = "Гроза";
                    break;

                case "thunderstorm-with-rain":
                    precipitation = "Дождь с грозой";
                    break;

                case "thunderstorm-with-hail":
                    precipitation = "Гроза с градом";
                    break;
                default:
                    precipitation = "Нет данных";

            }


//            java.util.Date timeConverter = new java.util.Date(time * 1000);
//            String st = String.format("%02d:%02d", timeConverter.getHours() + 3, timeConverter.getMinutes());
//            holder.textViewTimeOfUpdate.setText(st);


            Date date = new Date(time*1000);
            








            String st = String.format("%s:%s", date.getHours(), date.getMinutes());
            holder.textViewTemp.setText(factTemp);
            holder.textViewWindSpeed.setText(wind);
            holder.textViewTimeOfUpdate.setText(st);
            holder.textViewPrecipitation.setText(precipitation);
            holder.textViewCityName.setText(city);

        }


    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTimeOfUpdate;
        private TextView textViewTemp;
        private TextView textViewWindSpeed;
        private TextView textViewPrecipitation;
        private TextView textViewCityName;

        public WeatherViewHolder(@NonNull final View itemView) {
            super(itemView);
            textViewCityName = itemView.findViewById(R.id.textViewCityName);
            textViewTimeOfUpdate = itemView.findViewById(R.id.textViewTimeOfUpdate);
            textViewTemp = itemView.findViewById(R.id.textViewTemperature);
            textViewWindSpeed = itemView.findViewById(R.id.textViewWindValue);
            textViewPrecipitation = itemView.findViewById(R.id.textViewPrecipitationValue);


        }
    }
}
