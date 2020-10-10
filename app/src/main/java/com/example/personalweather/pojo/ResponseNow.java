package com.example.personalweather.pojo;

import android.util.Log;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.personalweather.converter.Converter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


@TypeConverters(Converter.class)
@Entity(tableName = "responseNow")
public class ResponseNow {


    @PrimaryKey(autoGenerate = true)
    public int id;

    @Ignore
    @SerializedName("fact")
    @Expose
    private JsonObject fact = null;


    @SerializedName("now")
    @Expose
    private long now;


    public JsonObject getFact() {
        return fact;
    }


    public void setFact(JsonObject fact) {
        this.fact = fact;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }
}

