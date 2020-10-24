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
import com.example.personalweather.dataBases.citiesDb.CitiesDB;
import com.example.personalweather.pojo.Cities;
import com.example.personalweather.pojo.ResponseNow;
import com.example.personalweather.pojo.WeatherOnNextDays;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private CitiesDB citiesDB;
    private Disposable disposable;




    private LiveData<ResponseNow> responseNowLiveData;
    private LiveData<List<Cities>> citiesLiveData;
    private  String lat = "55.755814";
    private  String lon = "37.617635";
    private String cityName = "Москва";


    public ViewModel(@NonNull Application application) {
        super(application);
        database = WeatherNowDB.getInstance(application);
        responseNowLiveData = database.getWeatherNowDao().getResponseNow();
        citiesDB = CitiesDB.getInstance(application);
        citiesLiveData = citiesDB.citiesDao().getCities();
        Log.i("SITI", citiesLiveData.getValue() + "");
        if(getCitiesLiveData().getValue() == null) {
            Cities cities = new Cities("Москва", "55.755814", "37.617635");
            Cities citiesNizhn = new Cities("Нижний Новгород", "56.331927", "44.023225");
            Cities citiesRost = new Cities("Ростов-на-Дону", "47.222078", "39.720349");
            Cities citiesSpb = new Cities("Санкт-Петербург", "59.939095", "30.315868");
            Cities citiesEkb = new Cities("Екатеринбург", "56.838011", "60.597465");
            Cities citiesNewYork = new Cities("Нью-Йорк", "40.714599", "-74.002791");
            Cities citiesBerlin = new Cities("Берлин", "52.519881", "13.407338");
            insertCities(cities);
            insertCities(citiesNizhn);
            insertCities(citiesRost);
            insertCities(citiesSpb);
            insertCities(citiesEkb);
            insertCities(cities);
            insertCities(citiesNewYork);
            insertCities(citiesBerlin);
        }



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
            responseNowLiveData = database.getWeatherNowDao().getResponseNow();
            return null;
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

    public void insertCities(Cities cities){
        InsertCitiesTask in = new InsertCitiesTask();
        try {
            in.execute(cities).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public class InsertCitiesTask extends AsyncTask<Cities, Void, Void>{


        @Override
        protected Void doInBackground(Cities... cities) {
            if(cities != null){
                citiesDB.citiesDao().insertCities(cities[0]);
            }
            return null;
        }
    }

    public LiveData<List<Cities>> getCitiesLiveData(){
        new getCitiesTask();
        return citiesLiveData;

    }




    public class getCitiesTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
           citiesLiveData = citiesDB.citiesDao().getCities();
           return null;
        }
    }

    public Cities search(String search){

            try {
                Cities cities = new SearchTask().execute(search).get();
                return cities;
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        return null;
    }

    public class SearchTask extends AsyncTask<String, Void, Cities>{

        @Override
        protected Cities doInBackground(String... strings) {
            String string = strings[0];
            List<Cities> cities = citiesDB.citiesDao().getAllWithNameLike(string);
            if(cities != null & cities.size() > 0) {
                Cities cities1 = cities.get(0);
                return cities1;
            }

            return null;

        }
    }






}
