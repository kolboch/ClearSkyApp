package com.example.kb.clearsky.model.api_specific;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherInfo {

    @SerializedName("temp")
    @Expose
    private Double temp;

    @SerializedName("temp_min")
    @Expose
    private Double tempMin;

    @SerializedName("temp_max")
    @Expose
    private Double tempMax;

    @SerializedName("pressure")
    @Expose
    private Double pressure;

    @SerializedName("sea_level")
    @Expose
    private Double seaLevel;

    @SerializedName("grnd_level")
    @Expose
    private Double groundLevel;

    @SerializedName("humidity")
    @Expose
    private Integer humidityPercent;

    @SerializedName("temp_kf")
    @Expose
    private Double tempKf;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(Double seaLevel) {
        this.seaLevel = seaLevel;
    }

    public Double getGroundLevel() {
        return groundLevel;
    }

    public void setGroundLevel(Double groundLevel) {
        this.groundLevel = groundLevel;
    }

    public Integer getHumidityPercent() {
        return humidityPercent;
    }

    public void setHumidityPercent(Integer humidityPercent) {
        this.humidityPercent = humidityPercent;
    }

    public Double getTempKf() {
        return tempKf;
    }

    public void setTempKf(Double tempKf) {
        this.tempKf = tempKf;
    }

}
