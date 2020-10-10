package com.example.personalweather.Api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {
    private static ApiFactory apiFactory = null;
    private static Retrofit retrofit;
    private static final Object LOCK = new Object();

    private static final String BASE_URL = "https://api.weather.yandex.ru/v2/";

    public static ApiFactory getInstance(){
        synchronized (LOCK) {
            if (apiFactory == null) {
                apiFactory = new ApiFactory();
            }
            return apiFactory;
        }

    }

    private ApiFactory(){
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static ApiService getApiService(){
        return retrofit.create(ApiService.class);
    }
}
