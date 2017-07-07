package com.example.kb.clearsky.model.connection_utils;

import com.example.kb.clearsky.model.api_specific.ResponseMainBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Karlo on 2017-07-07.
 */

public interface ApiService {

    @GET("forecast")
    Call<ResponseMainBody> getForecastFromCityID(@Query("id") Long cityID, @Query("appid")String apiKey);

    @GET("forecast")
    Call<ResponseMainBody>getForecastFromCityName(@Query("q")String cityName, @Query("appid")String apiKey);

    @GET("forecast")
    Call<ResponseMainBody>getForecastFromCoordinates(@Query("lat")Double latitude, @Query("lon")Double longitude, @Query("appid")String apiKey);
}
