package com.example.kb.clearsky.model.api_specific;

import com.example.kb.clearsky.model.City;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseMainBody {

    @SerializedName("cod")
    @Expose
    private String responseCode;

    @SerializedName("message")
    @Expose
    private Double message;

    @SerializedName("cnt")
    @Expose
    private Integer linesInResponse;

    @SerializedName("list")
    @Expose
    private java.util.List<Forecast> forecastList = null;

    @SerializedName("city")
    @Expose
    private City city;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Integer getLinesInResponse() {
        return linesInResponse;
    }

    public void setLinesInResponse(Integer linesInResponse) {
        this.linesInResponse = linesInResponse;
    }

    public java.util.List<Forecast> getForecastList() {
        return forecastList;
    }

    public void setForecastList(java.util.List<Forecast> forecastList) {
        this.forecastList = forecastList;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
