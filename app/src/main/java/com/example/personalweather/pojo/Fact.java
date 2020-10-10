package com.example.personalweather.pojo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.personalweather.converter.Converter;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;


//@Entity(tableName = "factTable")
public class Fact {

//    @PrimaryKey(autoGenerate = true)
//    public int id;


    @SerializedName("fact")
    @Expose
    private JsonObject fact;

    public JsonObject getFact() {
        return fact;
    }

    public void setFact(JsonObject fact) {
        this.fact = fact;
    }

    public Fact(JsonObject fact) {
        this.fact = fact;
    }
}
