package com.example.kb.clearsky.db_handlers;

import android.provider.BaseColumns;

/**
 * Created by Karol on 2017-07-11.
 */

public final class DbClearSkyContract {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "clearSky.db";

    private DbClearSkyContract() {
    }

    public static abstract class CountriesTable implements BaseColumns {
        public static final String TABLE_NAME = "countries";
        public static final String COL_1_COUNTRY_CODE = "country_code";
        public static final String COL_2_COUNTRY_NAME = "country_name";
    }

    public static abstract class CitiesTable implements BaseColumns {
        public static final String TABLE_NAME = "cities";
        public static final String COL_1_CITY_ID = "city_id";
        public static final String COL_2_CITY_NAME = "city_name";
        public static final String COL_3_COUNTRY_CODE = "country_code";
        public static final String COL_4_CITY_LONGITUDE = "city_longitude";
        public static final String COL_5_CITY_LATITUDE = "city_latitude";
    }

    public static abstract class UserPicks implements BaseColumns {
        public static final String TABLE_NAME = "user_picks";
        public static final String COL_1_CITY_ID = "city_id";
        public static final String COL_2_CITY_NAME = "city_name";
        public static final String COL_3_COUNTRY_CODE = "country_code";
        public static final String COL_4_CITY_LONGITUDE = "city_longitude";
        public static final String COL_5_CITY_LATITUDE = "city_latitude";
    }

}
