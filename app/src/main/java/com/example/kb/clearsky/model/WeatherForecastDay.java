package com.example.kb.clearsky.model;

/**
 * Created by Karlo on 2017-07-04.
 */

public class WeatherForecastDay {

    private static final int PREDICTIONS_PER_DAY = 8;
    private WeatherForecastSingle[] forecasts;

    public WeatherForecastDay() {
        this.forecasts = new WeatherForecastSingle[PREDICTIONS_PER_DAY];
    }

    public WeatherForecastDay(WeatherForecastSingle[] forecasts) {
        this.forecasts = forecasts;
    }

    public WeatherForecastSingle[] getForecasts() {
        return forecasts;
    }

    public WeatherForecastSingle getWeatherForecastSingle(int index) {
        return forecasts[index];
    }

    public void setWeatherForecastSingle(int index, WeatherForecastSingle forecast) {
        forecasts[index] = forecast;
    }
}
