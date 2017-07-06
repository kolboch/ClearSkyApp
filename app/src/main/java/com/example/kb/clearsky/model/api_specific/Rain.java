package com.example.kb.clearsky.model.api_specific;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rain {

    /**
     * rain volume for last 3 hours, mm (unit)
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
