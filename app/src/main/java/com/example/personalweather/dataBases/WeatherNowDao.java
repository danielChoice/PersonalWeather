package com.example.personalweather.dataBases;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.personalweather.pojo.ResponseNow;


@Dao
public interface WeatherNowDao {
    @Query("SELECT * FROM responseNow")
    LiveData<ResponseNow> getResponseNow();

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
