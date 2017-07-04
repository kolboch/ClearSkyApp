package com.example.kb.clearsky.model;

import com.example.kb.clearsky.model.api_specific.MyDate;
import com.example.kb.clearsky.model.api_specific.Temperature;
import com.example.kb.clearsky.model.api_specific.Weather;
import com.example.kb.clearsky.model.api_specific.Wind;

/**
 * Created by Karlo on 2017-07-04.
 */

public class WeatherForecastSingle {

    private MyDate date;
    private Temperature temperature;
    private float pressure;
    private float humidity;
    private Weather weather;
    private Wind wind;

    public WeatherForecastSingle(MyDate date, Temperature temperature, float pressure, float humidity, Weather weather, Wind wind) {
        this.date = date;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.weather = weather;
        this.wind = wind;
    }
}
