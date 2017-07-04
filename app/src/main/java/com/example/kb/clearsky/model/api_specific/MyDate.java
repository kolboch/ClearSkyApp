package com.example.kb.clearsky.model.api_specific;

/**
 * Created by Karlo on 2017-07-04.
 */

public class MyDate {

    private long dateUnix;
    private String dateText;

    public MyDate(long dateUnix, String dateText) {
        this.dateUnix = dateUnix;
        this.dateText = dateText;
    }

    public long getDateUnix() {
        return dateUnix;
    }

    public String getDateText() {
        return dateText;
    }
}
