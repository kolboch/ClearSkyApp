package com.example.kb.clearsky.model;

import com.example.kb.clearsky.model.api_specific.Forecast;

/**
 * Created by Karlo on 2017-07-04.
 */

public class WeatherForecastDay {

    private static final int PREDICTIONS_PER_DAY = 8;
    private Forecast[] forecasts;

    public WeatherForecastDay() {
        this.forecasts = new Forecast[PREDICTIONS_PER_DAY];
    }

    public WeatherForecastDay(Forecast[] forecasts) {
        this.forecasts = forecasts;
    }

    public Forecast[] getForecasts() {
        return forecasts;
    }

    public Forecast getWeatherForecastSingle(int index) {
        return forecasts[index];
    }

    public void setWeatherForecastSingle(int index, Forecast forecast) {
        forecasts[index] = forecast;
    }
}
