package com.example.personalweather.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalweather.R;

public class WeatherBlockAdapter extends RecyclerView.Adapter<WeatherBlockAdapter.weatherBlockViewHolder> {


    @NonNull
    @Override
    public weatherBlockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.today_block, parent, false);
        return new weatherBlockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull weatherBlockViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class weatherBlockViewHolder extends RecyclerView.ViewHolder {
        public weatherBlockViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}