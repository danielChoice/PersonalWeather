package com.example.personalweather.pojo;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class WeatherOnNextDays {
   private JsonArray forecasts;

    public JsonArray getForecasts() {
        return forecasts;
    }

    public void setForecasts(JsonArray forecasts) {
        this.forecasts = forecasts;
    }

    public WeatherOnNextDays(JsonArray forecasts) {
        this.forecasts = forecasts;
    }

    public String getDay1(){
        JsonObject jsonObject = (JsonObject) forecasts.get(0);
        String date = jsonObject.get("date").toString();
        date = date.replace("\"", "");
        return date;
    }

      public String getDay2(){
        JsonObject jsonObject = (JsonObject) forecasts.get(1);
        Log.i("LOLO", forecasts.get(0) + "");
        String date = jsonObject.get("date_ts").toString();
          date = date.replace("\"", "");

        return date;
    }

      public String getDay3(){
        JsonObject jsonObject = (JsonObject) forecasts.get(2);
        String date = jsonObject.get("date").toString();
        date = date.replace("\"", "");
        return date;
    }

      public String getDay4(){
        JsonObject jsonObject = (JsonObject) forecasts.get(3);
        String date = jsonObject.get("date").toString();
        date = date.replace("\"", "");
        return date;
    }

      public String getDay5(){
        JsonObject jsonObject = (JsonObject) forecasts.get(4);
        String date = jsonObject.get("date").toString();
        date = date.replace("\"", "");
        return date;
    }

      public String getDay6(){
        JsonObject jsonObject = (JsonObject) forecasts.get(5);
        String date = jsonObject.get("date").toString();
        date = date.replace("\"", "");
        return date;
    }

      public String getDay7(){
        JsonObject jsonObject = (JsonObject) forecasts.get(6);
        String date = jsonObject.get("date").toString();
        date = date.replace("\"", "");

        return date;
    }


}
