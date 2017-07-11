package com.example.kb.clearsky.connection_utils;

import com.example.kb.clearsky.model.api_specific.ResponseMainBody;

import retrofit2.Call;

/**
 * Created by Karol on 2017-07-09.
 */

public class ForecastCallGenerator {

    private static ForecastApiService service;

    static{
        service = ForecastRetroClient.getApiService();
    }

    private ForecastCallGenerator(){
    }

    public static Call<ResponseMainBody> getCall(String cityName, String unit, String apiKey){
        return service.getForecastFromCityName(cityName, unit, apiKey);
    }

    public static Call<ResponseMainBody> getCall(Long cityID, String unit, String apiKey){
        return service.getForecastFromCityID(cityID, unit, apiKey);
    }

    public static Call<ResponseMainBody> getCall(Double latitude, Double longitude, String unit, String apiKey){
        return service.getForecastFromCoordinates(latitude, longitude, unit, apiKey);
    }

}
