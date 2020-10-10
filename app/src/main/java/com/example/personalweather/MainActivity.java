package com.example.personalweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.personalweather.Api.ApiFactory;
import com.example.personalweather.Api.ApiService;
import com.example.personalweather.adapters.CitiesAdapter;
import com.example.personalweather.adapters.WeatherAdapter;
import com.example.personalweather.dataBases.ViewModel;
import com.example.personalweather.dataBases.WeatherNowDB;
import com.example.personalweather.pojo.Fact;
import com.example.personalweather.pojo.ResponseNow;
import com.google.gson.JsonObject;

import org.json.JSONException;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private WeatherAdapter adapter;
    private RecyclerView recyclerViewCities;
    private ViewModel viewModel;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerWeather);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WeatherAdapter();
        recyclerView.setAdapter(adapter);
        recyclerViewCities = findViewById(R.id.recyclerCities);
        recyclerViewCities.setLayoutManager(new GridLayoutManager(this, 2));
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        ResponseNow responseNow = viewModel.getResponse();
        Log.i("MyKey", responseNow.getNow() + "HELLO");
        adapter.setResponse(viewModel.getResponse());




    }

}