package com.example.dumy;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {




    @POST("/")
    Call<JsonObject>getsapi(@Query("temperature") String temp, @Query("pulse") String pulse, @Query("oxygen") String oxy);

}
