package com.example.kb.clearsky.model.database_specific;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Created by Karol on 2017-07-10.
 */

public class City {

    private Long cityID;
    private String cityName;
    private String countryCode;
    private Double longitude;
    private Double latitude;

    public City(Long cityID, String cityName, String countryCode, Double longitude, Double latitude) {
        this.cityID = cityID;
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public City(City other){
        this.cityID = other.getCityID();
        this.cityName = other.getCityName();
        this.countryCode = other.getCountryCode();
        this.longitude = other.getLongitude();
        this.latitude = other.getLatitude();
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00", DecimalFormatSymbols.getInstance(Locale.US));
        return String.format("%s (%s, %s)", cityName, df.format(latitude), df.format(longitude));
    }

    public Long getCityID() {
        return cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }
}
