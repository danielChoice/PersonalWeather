package com.example.personalweather.Api;

import com.example.personalweather.pojo.ResponseNow;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiService {


    @GET("forecast?")
    Observable<ResponseNow> getJsonWeather(@Query("lat") String lat,
                                            @Query("lon") String lon,
                                            @Header("X-Yandex-API-Key") String API_KEY,
                                            @Header("Content-Type") String contentType,
                                            @Header("Accept") String accept);
}
