package com.example.personalweather.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ConverterJsonArray {

    @TypeConverter
    public String JSONObjectFactToString(JsonArray jsonArray){

        return new Gson().toJson(jsonArray);
    }

    @TypeConverter
    public JsonArray getJsonObjectFactFromString(String string){
        Gson gson = new Gson();
        JsonArray jsonArray;
        jsonArray = gson.fromJson(string, JsonArray.class);

        return jsonArray;

    }
}
