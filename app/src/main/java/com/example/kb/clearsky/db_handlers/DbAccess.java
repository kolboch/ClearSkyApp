package com.example.kb.clearsky.db_handlers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kb.clearsky.db_handlers.DbClearSkyContract.CitiesTable;
import com.example.kb.clearsky.db_handlers.DbClearSkyContract.CountriesTable;
import com.example.kb.clearsky.model.database_specific.City;
import com.example.kb.clearsky.model.database_specific.Country;

import java.util.List;

/**
 * Created by Karol on 2017-07-10.
 */

public class DbAccess {

    private static final String LIKE = " LIKE ";
    private static final String PARAMETER_DYNAMIC = " ? ";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DbAccess instance;

    private DbAccess(Context context) {
        this.openHelper = new DbOpenHelper(context);
    }

    public static DbAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DbAccess(context);
        }
        return instance;
    }

    /**
     * open database connection
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * close database connection
     */
    public void close() {
        if (this.database != null) {
            this.database.close();
        }
    }

    public List<Country> getCountries() {
        Cursor cursor = getCountriesCursor();
        return DbUtils.collectCountries(cursor);
    }

    public Cursor getCountriesCursor() {
        Cursor cursor = database.query(true,
                CountriesTable.TABLE_NAME,
                new String[]{CountriesTable.COL_1_COUNTRY_CODE, CountriesTable.COL_2_COUNTRY_NAME},
                null, null, null, null, null, null);
        return cursor;
    }

    public List<City> getCitiesWithinCountry(Country country){
        Cursor cursor = getCitiesCursorWithinCountry(country);
        return DbUtils.collectCities(cursor);
    }

    public Cursor getCitiesCursorWithinCountry(Country country){
        String[] columns = new String[]{
                CitiesTable.COL_1_CITY_ID,
                CitiesTable.COL_2_CITY_NAME,
                CitiesTable.COL_3_COUNTRY_CODE,
                CitiesTable.COL_4_CITY_LONGITUDE,
                CitiesTable.COL_5_CITY_LATITUDE
        };
        String selection = CitiesTable.COL_3_COUNTRY_CODE + LIKE + PARAMETER_DYNAMIC;
        String[] selectionArgs = new String[]{country.getCountryCode()};
        Cursor cursor = database.query(true, CitiesTable.TABLE_NAME,
                columns,
                selection, selectionArgs,
                null, null, null, null);
        return cursor;
    }
}
