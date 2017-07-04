package com.example.kb.clearsky.model.api_specific;

/**
 * Created by Karlo on 2017-07-04.
 */

public class Temperature {

    private float temp;
    private float tempMin;
    private float tempMax;

    public Temperature(float temp, float tempMin, float tempMax) {
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public float getTemp() {
        return temp;
    }

    public float getTempMin() {
        return tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }
}
