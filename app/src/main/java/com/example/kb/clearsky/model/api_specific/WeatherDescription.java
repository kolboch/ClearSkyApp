package com.example.kb.clearsky.model.api_specific;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherDescription {

    @SerializedName("id")
    @Expose
    private Integer weatherID;

    @SerializedName("main")
    @Expose
    private String mainCategory;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("icon")
    @Expose
    private String iconCode;

    public Integer getWeatherID() {
        return weatherID;
    }

    public void setWeatherID(Integer weatherID) {
        this.weatherID = weatherID;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconCode() {
        return iconCode;
    }

    public void setIconCode(String iconCode) {
        this.iconCode = iconCode;
    }

}
