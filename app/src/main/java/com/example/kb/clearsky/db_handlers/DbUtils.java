package com.example.kb.clearsky.db_handlers;

import android.database.Cursor;

import com.example.kb.clearsky.db_handlers.DbClearSkyContract.CountriesTable;
import com.example.kb.clearsky.model.database_specific.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karol on 2017-07-11.
 */

public class DbUtils {

    private DbUtils(){
    }

    public static List<Country> collectCountries(Cursor countriesCursor){
        List<Country> list = new ArrayList<>();
        Cursor cc = countriesCursor;
        cc.moveToFirst();
        while(!cc.isAfterLast()){
            list.add(new Country(
                    cc.getString(cc.getColumnIndex(CountriesTable.COL_2_COUNTRY_NAME)),
                    cc.getString(cc.getColumnIndex(CountriesTable.COL_1_COUNTRY_CODE))));
            cc.moveToNext();
        }
        cc.close();
        return list;
    }
}
