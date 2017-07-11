package com.example.kb.clearsky.db_handlers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kb.clearsky.db_handlers.DbClearSkyContract.CountriesTable;
import com.example.kb.clearsky.model.database_specific.Country;

import java.util.List;

/**
 * Created by Karol on 2017-07-10.
 */

public class DbAccess {

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
        List<Country> list;
        Cursor cursor = database.query(true, CountriesTable.TABLE_NAME,
                new String[]{CountriesTable.COL_1_COUNTRY_CODE, CountriesTable.COL_2_COUNTRY_NAME},
                null, null, null, null, null, null);
        list = DbUtils.collectCountries(cursor);
        return list;
    }

    public Cursor getCountriesCursor() {
        Cursor cursor = database.query(true,
                CountriesTable.TABLE_NAME,
                new String[]{CountriesTable.COL_1_COUNTRY_CODE, CountriesTable.COL_2_COUNTRY_NAME},
                null, null, null, null, null, null);
        return cursor;
    }

//    TODO queries needed
//     --- usage example ---
//     DbAccess databaseAccess = DbAccess.getInstance(this);
//     databaseAccess.open();
//     List<String> quotes = databaseAccess.getQuotes();
//     databaseAccess.close();

}
