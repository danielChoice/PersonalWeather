package com.example.personalweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.personalweather.dataBases.ViewModel;
import com.example.personalweather.dataBases.citiesDb.CitiesDB;
import com.example.personalweather.pojo.Cities;

import org.w3c.dom.Text;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText editText;
    private ViewModel viewModel;
    private TextView citietw;
    Cities cities = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editText = findViewById(R.id.editTextSearch);
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        citietw = findViewById(R.id.textViewCities);
        ((TextView) editText).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cities = viewModel.search(charSequence.toString());
                if (cities != null) {
                    citietw.setText(cities.getCityName());
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void searchOnClick(View view) {
        if (cities != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("CITYNAME", cities.getCityName());
            intent.putExtra("CITYLAT", cities.getLat());
            intent.putExtra("CITYLON", cities.getLon());
            startActivity(intent);


        }
    }
}