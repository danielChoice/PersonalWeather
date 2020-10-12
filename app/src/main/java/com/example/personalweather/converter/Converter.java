package com.example.personalweather.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.concurrent.locks.Lock;


public class Converter {



    @TypeConverter
    public String JSONObjectFactToString(JsonObject jsonObject){

        return new Gson().toJson(jsonObject);
    }

    @TypeConverter
    public JsonObject getJsonObjectFactFromString(String string){
        Gson gson = new Gson();
        JsonObject objectFact;
        objectFact = gson.fromJson(string, JsonObject.class);

        return objectFact;

    }

    @TypeConverter
    public String JSONArrayToString(JsonArray jsonArray){

        return new Gson().toJson(jsonArray);
    }

    @TypeConverter
    public JsonArray getArrayFromString(String string){
        Gson gson = new Gson();
        JsonArray jsonArray;
        jsonArray = gson.fromJson(string, JsonArray.class);

        return jsonArray;

    }

}





