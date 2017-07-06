package com.example.kb.clearsky.model.api_specific;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    @Expose
    private Integer cloudinessPercent;

    public Integer getCloudinessPercent() {
        return cloudinessPercent;
    }

    public void setCloudinessPercent(Integer cloudinessPercent) {
        this.cloudinessPercent = cloudinessPercent;
    }

}
