package com.example.personalweather.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalweather.City;
import com.example.personalweather.R;

import java.util.ArrayList;
import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder> {

    private ArrayList<City> cities;
    private OnPosterClickListener onPosterClickListener;

    public ArrayList<City> getCities() {
        return cities;
    }

    public interface OnPosterClickListener {
        void onPosterClick(int position);
    }

    public OnPosterClickListener getOnPosterClickListener(OnPosterClickListener onPosterClickListener) {
        return this.onPosterClickListener;
    }

    public void setOnPosterClickListener(OnPosterClickListener onPosterClickListener) {
        this.onPosterClickListener = onPosterClickListener;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cities_item, parent, false);
        return new CitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesViewHolder holder, int position) {
        City city = cities.get(position);
        String name = city.getName();
        holder.cityName.setText(name);


    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class CitiesViewHolder extends RecyclerView.ViewHolder {
        private TextView cityName;
        public CitiesViewHolder(@NonNull View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.textViewCityInRecycler);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onPosterClickListener != null){
                        onPosterClickListener.onPosterClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
