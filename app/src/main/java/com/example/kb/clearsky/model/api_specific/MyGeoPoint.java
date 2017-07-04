package com.example.kb.clearsky.model.api_specific;

/**
 * Created by Karlo on 2017-07-04.
 */

public class MyGeoPoint {

    private float latitude;
    private float longitude;

    public MyGeoPoint(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
