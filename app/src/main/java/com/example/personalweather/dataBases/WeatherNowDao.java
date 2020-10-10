package com.example.personalweather.dataBases;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.personalweather.pojo.Fact;
import com.example.personalweather.pojo.ResponseNow;

import java.util.List;



@Dao
public interface WeatherNowDao {
    @Query("SELECT * FROM responseNow")
    ResponseNow getResponseNow();

    @Query("DELETE FROM responseNow")
    void deleteResponseNow();

    @Insert
    void insertResponse(ResponseNow responseNow);

//    @Query("SELECT * FROM factTable")
//    Fact getFact();
//
//    @Query("DELETE FROM factTable")
//    void deleteFact();
//
//    @Insert
//    void insertFact(Fact fact);


}
