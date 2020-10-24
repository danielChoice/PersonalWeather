package com.example.personalweather.dataBases.citiesDb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.personalweather.pojo.Cities;
import com.example.personalweather.pojo.ResponseNow;

@Database(entities = {Cities.class}, version = 4, exportSchema = false)
public abstract class CitiesDB extends RoomDatabase {
    private static CitiesDB database = null;
    private static final String dbName = "cities.db";
    private static final Object LOCK = new Object();

    public static CitiesDB getInstance(Context context){
        synchronized (LOCK){
        if (database == null){
            database = Room.databaseBuilder(context, CitiesDB.class, dbName).fallbackToDestructiveMigration().build();
        }return database;
        }
    }

    public abstract CitiesDao citiesDao();
}
