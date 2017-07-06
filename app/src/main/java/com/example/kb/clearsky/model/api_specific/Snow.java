package com.example.kb.clearsky.model.api_specific;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Karlo on 2017-07-06.
 */

public class Snow {

    /**
     * snow volume for last 3 hours
     */
    @SerializedName("3h")
    @Expose
    private Double volume;

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

}
