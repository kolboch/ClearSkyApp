package com.example.kb.clearsky.model;


import com.example.kb.clearsky.model.api_specific.MyGeoPoint;

/**
 * Created by Karlo on 2017-07-04.
 */

public class City {

    private long id;
    private String name;
    private MyGeoPoint coordinates;
    private String countryName;

    public City(long id, String name, MyGeoPoint coordinates, String countryName) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.countryName = countryName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyGeoPoint getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(MyGeoPoint coordinates) {
        this.coordinates = coordinates;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
