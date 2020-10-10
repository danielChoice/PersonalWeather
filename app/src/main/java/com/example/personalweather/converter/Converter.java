package com.example.personalweather.converter;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.personalweather.pojo.Fact;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.converter.gson.GsonConverterFactory;


public class Converter {
    @TypeConverter
    public String JSONObjectFactToString(JsonObject fact){
        return new Gson().toJson(fact);
    }

    @TypeConverter
    public JsonObject getJsonObjectFactFromString(String string){
        Gson gson = new Gson();
        JsonObject objectFact = null;
        JsonArray object = gson.fromJson(string, JsonArray.class);
        objectFact = object.getAsJsonObject();


        return objectFact;

    }


}
