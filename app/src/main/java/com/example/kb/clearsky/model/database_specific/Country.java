package com.example.kb.clearsky.model.database_specific;

/**
 * Created by Karol on 2017-07-10.
 */

public class Country {

    private String countryName;
    private String countryCode;

    public Country(String countryName, String countryCode){
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    @Override
    public String toString() {
        return countryName + ", " + countryCode;
    }
}
