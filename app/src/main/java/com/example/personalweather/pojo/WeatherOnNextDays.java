package com.example.personalweather.pojo;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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
        String result = "";
        JsonObject jsonObject = (JsonObject) forecasts.get(0);
        String date = jsonObject.get("date").toString();

        date = date.replace("\"", "");

        SimpleDateFormat oldDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat newDate = new SimpleDateFormat("dd MMMM");
        try {
            Date date1 = oldDate.parse(date);
            result = newDate.format(date1);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

      public Date getDay2(){
       Date result = new Date();
        JsonObject jsonObject = (JsonObject) forecasts.get(1);
        String date = jsonObject.get("date").toString();
        date = date.replace("\"", "");
          SimpleDateFormat oldDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
          SimpleDateFormat newDate = new SimpleDateFormat("dd MMMM");

          try {
              result = oldDate.parse(date);





          } catch (ParseException e) {
              e.printStackTrace();
          }

        return result;
    }

      public Date getDay3(){
          Date result = new Date();
          JsonObject jsonObject = (JsonObject) forecasts.get(2);
          String date = jsonObject.get("date").toString();
          Log.i("DATEEE", date);
          date = date.replace("\"", "");
          SimpleDateFormat oldDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
          SimpleDateFormat newDate = new SimpleDateFormat("dd MMMM");

          try {
              result = oldDate.parse(date);





          } catch (ParseException e) {
              e.printStackTrace();
          }

          return result;
    }

      public Date getDay4(){
          Date result = new Date();
          JsonObject jsonObject = (JsonObject) forecasts.get(3);
          String date = jsonObject.get("date").toString();
          Log.i("DATEEE", date);
          date = date.replace("\"", "");
          SimpleDateFormat oldDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
          SimpleDateFormat newDate = new SimpleDateFormat("dd MMMM");

          try {
              result = oldDate.parse(date);





          } catch (ParseException e) {
              e.printStackTrace();
          }

          return result;
    }

      public Date getDay5(){
          Date result = new Date();
          JsonObject jsonObject = (JsonObject) forecasts.get(4);
          String date = jsonObject.get("date").toString();
          Log.i("DATEEE", date);
          date = date.replace("\"", "");
          SimpleDateFormat oldDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
          SimpleDateFormat newDate = new SimpleDateFormat("dd MMMM");

          try {
              result = oldDate.parse(date);





          } catch (ParseException e) {
              e.printStackTrace();
          }

          return result;
    }

      public Date getDay7(){
          Date result = new Date();
          JsonObject jsonObject = (JsonObject) forecasts.get(6);
          String date = jsonObject.get("date").toString();
          Log.i("DATEEE", date);
          date = date.replace("\"", "");
          SimpleDateFormat oldDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
          SimpleDateFormat newDate = new SimpleDateFormat("dd MMMM");

          try {
              result = oldDate.parse(date);





          } catch (ParseException e) {
              e.printStackTrace();
          }

          return result;
    }

 public Date getDay6(){
          Date result = new Date();
          JsonObject jsonObject = (JsonObject) forecasts.get(5);
          String date = jsonObject.get("date").toString();
          Log.i("DATEEE", date);
          date = date.replace("\"", "");
          SimpleDateFormat oldDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
          SimpleDateFormat newDate = new SimpleDateFormat("dd MMMM");

          try {
              result = oldDate.parse(date);





          } catch (ParseException e) {
              e.printStackTrace();
          }

          return result;
    }


}


