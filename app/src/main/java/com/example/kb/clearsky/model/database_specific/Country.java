package com.example.kb.clearsky.model.database_specific;

/**
 * Created by Karol on 2017-07-10.
 */

public class Country {

    private String countryName;
    private String countryCode;

    public Country() {
    }

    public Country(Country other) {
        this.countryName = other.getCountryName();
        this.countryCode = other.getCountryCode();
    }

    @Override
    public boolean equals(Object obj) {
        boolean areEqual = false;
        if (this == obj) {
            areEqual = true;
        } else if (obj == null) {
            areEqual = false;
        } else if (obj instanceof Country) {
            Country other = (Country) obj;
            String otherCName = other.getCountryName();
            String otherCCode = other.getCountryCode();
            areEqual = (countryName == null ? other.getCountryName() == null : countryName.equals(otherCName))
                    && (countryCode == null ? other.getCountryCode() == null : countryCode.equals(otherCCode));
        }
        return areEqual;
    }

    public Country(String countryName, String countryCode) {
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return countryName + ", " + countryCode;
    }
}
