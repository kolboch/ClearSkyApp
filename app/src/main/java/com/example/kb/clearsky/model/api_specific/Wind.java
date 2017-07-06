package com.example.kb.clearsky.model.api_specific;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    @Expose
    private Double windSpeed;

    /**
     * wind direction represented in degrees ( meteorological)
     */
    @SerializedName("deg")
    @Expose
    private Double direction;

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getDirection() {
        return direction;
    }

    public void setDirection(Double direction) {
        this.direction = direction;
    }

}
