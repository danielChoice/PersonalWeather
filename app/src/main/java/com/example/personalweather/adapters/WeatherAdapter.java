package com.example.personalweather.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalweather.R;
import com.example.personalweather.pojo.ResponseNow;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private ResponseNow response = null;

    public ResponseNow getResponse() {
        return response;
    }

    public void setResponse(ResponseNow response) {
        this.response = response;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new WeatherViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        if (response != null) {
            long time = response.getNow();
            java.util.Date timeConverter =new java.util.Date(time*1000);
            String st = String.format("%02d:%02d", timeConverter.getHours() + 3, timeConverter.getMinutes());
            holder.textViewTimeOfUpdate.setText(st);
        }


    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTimeOfUpdate;
        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTimeOfUpdate = itemView.findViewById(R.id.textViewTimeOfUpdate);

        }
    }
}
