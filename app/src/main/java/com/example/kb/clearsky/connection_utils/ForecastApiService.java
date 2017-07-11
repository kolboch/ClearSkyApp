package com.example.kb.clearsky.connection_utils;

import com.example.kb.clearsky.model.api_specific.ResponseMainBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Karlo on 2017-07-07.
 */

public interface ForecastApiService {

    @GET("forecast")
    Call<ResponseMainBody> getForecastFromCityID(@Query("id") Long cityID, @Query("units") String unit, @Query("appid") String apiKey);

    @GET("forecast")
    Call<ResponseMainBody> getForecastFromCityName(@Query("q") String cityName, @Query("units") String unit, @Query("appid") String apiKey);

    @GET("forecast")
    Call<ResponseMainBody> getForecastFromCoordinates(@Query("lat") Double latitude, @Query("lon") Double longitude, @Query("units") String unit, @Query("appid") String apiKey);

}
