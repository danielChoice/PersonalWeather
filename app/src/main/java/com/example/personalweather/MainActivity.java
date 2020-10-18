package com.example.personalweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.personalweather.adapters.CitiesAdapter;
import com.example.personalweather.adapters.WeatherAdapter;
import com.example.personalweather.adapters.WeatherBlockAdapter;
import com.example.personalweather.adapters.WeatherNextDayAdapter;
import com.example.personalweather.dataBases.ViewModel;
import com.example.personalweather.pojo.ResponseNow;
import com.example.personalweather.pojo.WeatherOnNextDays;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerViewBlock;
    private RecyclerView recyclerView;
    private WeatherAdapter adapter;
    private ViewModel viewModel;
    private WeatherBlockAdapter blockAdapter;
    private WeatherNextDayAdapter nextDayAdapter;
    private RecyclerView recyclerViewWeatherNextDays;
    private WeatherOnNextDays weatherOnNextDays;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerWeather);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WeatherAdapter();
        blockAdapter = new WeatherBlockAdapter();
        recyclerView.setAdapter(adapter);

        nextDayAdapter = new WeatherNextDayAdapter();
        recyclerViewWeatherNextDays = findViewById(R.id.recyclerWeatherOnNextDays);
        recyclerViewWeatherNextDays.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewWeatherNextDays.setAdapter(nextDayAdapter);

        recyclerViewBlock = findViewById(R.id.recyclerWeatherNextDay);
        recyclerViewBlock.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewBlock.setAdapter(blockAdapter);
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);

        viewModel.getResponse().observe(this, new Observer<ResponseNow>() {
            @Override
            public void onChanged(ResponseNow responseNow) {
                if (responseNow != null) {
                    adapter.setResponse(responseNow);
                    adapter.setCity(viewModel.getCityName());
                    LiveData<ResponseNow> responseNow1 = viewModel.getResponse();
                    ResponseNow responseNow2 = responseNow1.getValue();
                    Log.i("MyKey", responseNow2.getFact() + "");
                    JsonArray jsonArray = responseNow.getForecasts();
                    weatherOnNextDays = new WeatherOnNextDays(jsonArray);
                    Log.i("DATE", weatherOnNextDays.getDay7());
                    nextDayAdapter.setWeatherOnNextDays(weatherOnNextDays);



                }
            }
        });





    }


}


