package com.example.personalweather.dataBases;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.personalweather.Api.ApiFactory;
import com.example.personalweather.Api.ApiService;
import com.example.personalweather.pojo.ResponseNow;
import com.google.gson.JsonObject;

import java.util.concurrent.ExecutionException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ViewModel extends AndroidViewModel {
    private static String testLat = "55.75396";
    private static String testLon = "37.620393";
    private static String API_KEY = "65a2d3f1-1410-4837-be10-aa010cb167bd";
    private static String acpCont = "application/xml";
    private WeatherNowDB database;
    private Disposable disposable;
    private LiveData<ResponseNow> responseNowLiveData;
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

    private void loadData(){
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();

        disposable = apiService.getJsonWeather(testLat, testLon, API_KEY, acpCont, acpCont)
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

    public void deleteAllFromResponse(){}

    public class deleteAllTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            database.getWeatherNowDao().deleteResponseNow();
            return null;
        }
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
//    public class getResponseFactTask extends AsyncTask<Void, Void, JsonObject>{
//
//        @Override
//        protected JsonObject doInBackground(Void... voids) {
//            JsonObject jsonObjectFact = database.getWeatherNowDao().getResponseNow().getFact();
//            return jsonObjectFact;
//        }
//    }





}
