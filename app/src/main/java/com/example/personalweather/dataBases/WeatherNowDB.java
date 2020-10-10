package com.example.personalweather.dataBases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.personalweather.pojo.ResponseNow;

@Database(entities = {ResponseNow.class}, version = 9, exportSchema = false)
public abstract class WeatherNowDB extends RoomDatabase {
    private static WeatherNowDB database = null;
    private static final String DB_NAME = "weather.db";
    private static final Object LOCK = new Object();
    public static WeatherNowDB getInstance(Context context){
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, WeatherNowDB.class, DB_NAME).fallbackToDestructiveMigration().build();
            }
            return database;
        }

    }

    public abstract WeatherNowDao getWeatherNowDao();
}
