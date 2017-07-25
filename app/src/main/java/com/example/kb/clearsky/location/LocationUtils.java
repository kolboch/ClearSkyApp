package com.example.kb.clearsky.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import java.util.List;

/**
 * Created by Karlo on 2017-07-16.
 */

public class LocationUtils {

    private static final int REQUEST_CODE_PERM_LOCATIONS = 200;
    private static Criteria criteria;

    static{
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
    }

    public static Location getLocation(Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = manager.getProviders(true);
        Location location;
        //TODO surround with permissions
        manager.requestSingleUpdate(criteria, new MyLocationListener(), null);

    }

}
