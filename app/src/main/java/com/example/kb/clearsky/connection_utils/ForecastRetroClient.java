package com.example.kb.clearsky.connection_utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Karlo on 2017-07-07.
 */

public class ForecastRetroClient {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ForecastApiService getApiService() {
        return getRetrofitInstance().create(ForecastApiService.class);
    }
}
