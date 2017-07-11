package com.example.kb.clearsky.db_handlers;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Karol on 2017-07-10.
 */

public class DbOpenHelper extends SQLiteAssetHelper{

    private static final String DATABASE_NAME = DbClearSkyContract.DB_NAME;
    private static final int DATABASE_VERSION = DbClearSkyContract.DB_VERSION;

    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
