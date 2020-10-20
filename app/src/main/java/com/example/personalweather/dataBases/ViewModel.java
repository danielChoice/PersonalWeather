package com.example.personalweather.dataBases;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.personalweather.Api.ApiFactory;
import com.example.personalweather.Api.ApiService;
import com.example.personalweather.City;
import com.example.personalweather.adapters.WeatherNextDayAdapter;
import com.example.personalweather.pojo.ResponseNow;
import com.example.personalweather.pojo.WeatherOnNextDays;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class ViewModel extends AndroidViewModel {

    private static String API_KEY = "65a2d3f1-1410-4837-be10-aa010cb167bd";
    private static String acpCont = "application/xml";
    private WeatherNowDB database;
    private Disposable disposable;




    private LiveData<ResponseNow> responseNowLiveData;
    private ArrayList<City> cityArrayList;
    private  String lat = "55.753960";
    private  String lon = "37.620393";
    private String cityName = "Москва";


    public ViewModel(@NonNull Application application) {
        super(application);
        database = WeatherNowDB.getInstance(application);
        responseNowLiveData = database.getWeatherNowDao().getResponseNow();
        loadData();

    }

    public void insertResponse(ResponseNow responseNow){
        new InsertResponseTask().execute(responseNow);

    }

    public class InsertResponseTask extends AsyncTask<ResponseNow, Void, Void>{

        @Override
        protected Void doInBackground(ResponseNow... responseNows) {
            if (responseNows != null){
                database.getWeatherNowDao().deleteResponseNow();
                database.getWeatherNowDao().insertResponse(responseNows[0]);
            }
            return null;
        }
    }

    public LiveData<ResponseNow> getResponse(){
        return responseNowLiveData;

    }

    public class getResponseTask extends AsyncTask<Void, Void, LiveData<ResponseNow>>{

        @Override
        protected LiveData<ResponseNow> doInBackground(Void... voids) {
            LiveData<ResponseNow> responseNow = database.getWeatherNowDao().getResponseNow();
            return responseNow;
        }
    }

    public void loadData(){
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();

        disposable = apiService.getJsonWeather(lat, lon, API_KEY, acpCont, acpCont)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseNow>() {
                    @Override
                    public void accept(ResponseNow responseNow) throws Exception {
                        if(responseNow != null){
                            insertResponse(responseNow);











                        }



                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("MyKey", throwable.getMessage());

                    }
                });
    }

    public void deleteAllFromResponse(){
        new deleteAllTask().execute();
    }

    public class deleteAllTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            database.getWeatherNowDao().deleteResponseNow();
            return null;
        }
    }

    public static String getApiKey() {
        return API_KEY;
    }

    public static void setApiKey(String apiKey) {
        API_KEY = apiKey;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

//    public JsonObject getResponseFact(){
//        JsonObject jsonObject= null;
//
//        try {
//            jsonObject = new getResponseFactTask().execute().get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return jsonObject;
//
//    }
//
//    private class getResponseFactTask extends AsyncTask<Void, Void, JsonObject>{
//
//        @Override
//        protected JsonObject doInBackground(Void... voids) {
//            JsonObject jsonObjectFact = null;
//            LiveData<ResponseNow> responseNow = database.getWeatherNowDao().getResponseNow();
//            ResponseNow responseNow1 = responseNow.getValue();
//            if (responseNow1 != null){
//                jsonObjectFact = responseNow1.getFact();
//            }
//
//            return jsonObjectFact;
//        }
//    }





}
