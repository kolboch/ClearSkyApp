package com.example.kb.clearsky.model.api_specific;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Karlo on 2017-07-04.
 */

public class MyDate {

    private long dateUnix;
    private Date date;

    public MyDate(long dateUnix) {
        this.dateUnix = dateUnix;
        this.date = new Date(dateUnix);
    }

    public long getDateUnix() {
        return dateUnix;
    }

    public Date getDate() {
        return date;
    }

    public String getDate(String datePattern) {
        SimpleDateFormat df = new SimpleDateFormat(datePattern);
        return df.format(date);
    }
}
