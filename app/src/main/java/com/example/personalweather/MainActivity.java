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
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.personalweather.adapters.CitiesAdapter;
import com.example.personalweather.adapters.WeatherAdapter;
import com.example.personalweather.dataBases.ViewModel;
import com.example.personalweather.pojo.ResponseNow;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private WeatherAdapter adapter;
    private RecyclerView recyclerViewCities;
    private ViewModel viewModel;
    private CitiesAdapter citiesAdapter;
    private City city;
    private ArrayList<City> cityArrayList;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchCity;
    private Boolean sortType = false;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerWeather);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        switchCity = (Switch) findViewById(R.id.switchCity);
        adapter = new WeatherAdapter();
        recyclerView.setAdapter(adapter);
        recyclerViewCities = findViewById(R.id.recyclerCities);
        recyclerViewCities.setLayoutManager(new GridLayoutManager(this, 2));
        citiesAdapter = new CitiesAdapter();
        city = new City();
        citiesAdapter.setCities(city.getRussianCities());
        citiesAdapter.setOnPosterClickListener(new CitiesAdapter.OnPosterClickListener() {
            @Override
            public void onPosterClick(int position) {
                if(!sortType){
                    city = city.getRussianCities().get(position);
                    viewModel.setLat(city.getLatitude());
                    viewModel.setLon(city.getLongitude());
                    viewModel.setCityName(city.getName());
                    viewModel.loadData();

                }else {
                    city = city.getWorldCities().get(position);
                    viewModel.setLat(city.getLatitude());
                    viewModel.setLon(city.getLongitude());
                    viewModel.setCityName(city.getName());
                    viewModel.loadData();
                }

            }
        });
        recyclerViewCities.setAdapter(citiesAdapter);
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getResponse().observe(this, new Observer<ResponseNow>() {
            @Override
            public void onChanged(ResponseNow responseNow) {
                if(responseNow != null){
                    adapter.setResponse(responseNow);
                    adapter.setCity(viewModel.getCityName());
                    LiveData<ResponseNow> responseNow1 = viewModel.getResponse();
                    ResponseNow responseNow2 = responseNow1.getValue();
                    Log.i("MyKey",responseNow2.getFact() + "");




                }
            }
        });

        switchCity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b){
                    citiesAdapter.setCities(city.getRussianCities());
                    sortType = false;






                }else {
                    citiesAdapter.setCities(city.getWorldCities());
                    sortType = true;
                }


            }


        });






    }



}