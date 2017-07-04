package com.example.kb.clearsky.model.api_specific;

/**
 * Created by Karlo on 2017-07-04.
 */

public class Weather {

    private int weatherId;
    private String mainCategory;
    private String description;
    private String iconCode;

    public Weather(int weatherId, String mainCategory, String description, String iconCode) {
        this.weatherId = weatherId;
        this.mainCategory = mainCategory;
        this.description = description;
        this.iconCode = iconCode;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public String getDescription() {
        return description;
    }

    public String getIconCode() {
        return iconCode;
    }
}
