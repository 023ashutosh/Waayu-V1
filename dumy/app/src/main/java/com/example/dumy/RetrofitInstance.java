package com.example.dumy;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    static final String BASE_URL="https://predict-sih.herokuapp.com";

    static Retrofit getInstance()
    {
        Retrofit.Builder builder=new Retrofit.Builder();
        builder.baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient());
        return builder.build();
    }
}
