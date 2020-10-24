package com.example.personalweather.dataBases.citiesDb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.personalweather.pojo.Cities;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


@Dao
public interface CitiesDao {
    @Query("SELECT * FROM cities")
    LiveData<List<Cities>> getCities();

    @Query("SELECT * FROM cities WHERE cityName Like '%' || :search || '%'")
    List<Cities> getAllWithNameLike(String search);

    @Query("DELETE FROM cities")
    void deleteCities();

    @Insert
    void insertCities(Cities cities);
}
