package com.example.personalweather.pojo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "cities")
public class Cities {

   @PrimaryKey(autoGenerate = true)
   private int id;
   private String cityName;
   private String lat;
   private String lon;

   public Cities(String cityName, String lat, String lon) {
      this.cityName = cityName;
      this.lat = lat;
      this.lon = lon;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getCityName() {
      return cityName;
   }

   public void setCityName(String cityName) {
      this.cityName = cityName;
   }

   public String getLat() {
      return lat;
   }

   public void setLat(String lat) {
      this.lat = lat;
   }

   public String getLon() {
      return lon;
   }

   public void setLon(String lon) {
      this.lon = lon;
   }
}
