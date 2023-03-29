package com.example.dumy;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("data/2.5/weather")
    Call<JsonObject> gettemp(@Query("lat")Double latjson,@Query("lon")Double lonjson, @Query("appid") String key);

}
