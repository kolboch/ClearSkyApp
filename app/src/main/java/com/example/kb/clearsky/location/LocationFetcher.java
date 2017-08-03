package com.example.kb.clearsky.location;

import android.Manifest.permission;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.kb.clearsky.R;

/**
 * Created by Karlo on 2017-07-16.
 */

public class LocationFetcher {

    public static final int REQUEST_CODE_PERM_LOCATIONS = 701;

    private static final String LOG_TAG = LocationFetcher.class.getSimpleName();
    private static Criteria criteriaCoarse;
    private static Criteria criteriaFine;
    private static final String[] PROVIDERS_COARSE;
    private static final String[] PROVIDERS_FINE;
    private static final String COARSE_LOCATION = permission.ACCESS_COARSE_LOCATION;
    private static final String FINE_LOCATION = permission.ACCESS_FINE_LOCATION;
    private static final long INTERVAL = 1000;
    private static final float MIN_DIST = 1;

    private Context context;
    private View parentView;
    private Location location;
    private AlertDialog alertDialog;
    private LocationManager manager;
    private boolean askedToEnable;

    static {
        setCriteriaCoarse();
        setCriteriaFine();
        PROVIDERS_COARSE = new String[]{
                LocationManager.PASSIVE_PROVIDER,
                LocationManager.NETWORK_PROVIDER
        };
        PROVIDERS_FINE = new String[]{
                LocationManager.PASSIVE_PROVIDER,
                LocationManager.GPS_PROVIDER
        };
    }

    public LocationFetcher(Context context, View parentView) {
        this.context = context;
        this.parentView = parentView;
        this.location = null;
        this.manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        this.askedToEnable = false;
    }

    public void updateLocation() {
        try {
            getCoarseLocation();
            if (location == null || isOutdated(location)) {
                getFineLocation();
            }
        } catch (SecurityException e) {
            Log.e(LOG_TAG, "Security exception. Tried action without valid permission.");
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    public void removeUpdates() {
        this.manager.removeUpdates((LocationListener) context);
    }


    public void dispose() {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    public void enableAskingForPermission() {
        askedToEnable = false;
    }

    public Location getLocation() {
        return location;
    }

    private void getCoarseLocation() {
        if (ActivityCompat.checkSelfPermission(context, COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            askForPermission(context, parentView, COARSE_LOCATION);
        }
        if (ActivityCompat.checkSelfPermission(context, COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && (!manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || !manager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER))
                && !askedToEnable) {
            buildAlertMessage(R.string.wireless_disabled_switch_on);
            askedToEnable = true;
        }
        for (int i = 0; i < PROVIDERS_COARSE.length && location == null && !isOutdated(location); i++) {
            manager.requestSingleUpdate(criteriaCoarse, (LocationListener) context, null);
            manager.requestLocationUpdates(PROVIDERS_COARSE[i], INTERVAL, MIN_DIST, (LocationListener) context);
            Location lastKnown = manager.getLastKnownLocation(PROVIDERS_COARSE[i]);
            if (lastKnown != null && !isOutdated(lastKnown)) {
                location = lastKnown;
            }
        }
    }

    private void getFineLocation() {
        if (ActivityCompat.checkSelfPermission(context, FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            askForPermission(context, parentView, FINE_LOCATION);
        }
        if (ActivityCompat.checkSelfPermission(context, FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && !manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && !askedToEnable) {
            buildAlertMessage(R.string.gps_disabled_switch_on);
            askedToEnable = true;
        }
        for (int i = 0; i < PROVIDERS_FINE.length && location == null && !isOutdated(location); i++) {
            manager.requestSingleUpdate(criteriaFine, (LocationListener) context, null);
            manager.requestLocationUpdates(PROVIDERS_FINE[i], INTERVAL, MIN_DIST, (LocationListener) context);
            Location lastKnown = manager.getLastKnownLocation(PROVIDERS_FINE[i]);
            if (lastKnown != null && !isOutdated(lastKnown)) {
                location = lastKnown;
            }
        }
    }

    private void askForPermission(final Context context, final View parentView, String permissionRequested) {
        if (ActivityCompat.checkSelfPermission(context, permissionRequested) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                    permissionRequested)) {
                final Snackbar snackbar = Snackbar.make(parentView, R.string.permission_location_info, Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction(R.string.got_it, new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        updateLocation();
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
                Log.v(LOG_TAG, "Explaining why permission is needed.");
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{permissionRequested},
                        REQUEST_CODE_PERM_LOCATIONS);
                Log.v(LOG_TAG, "Asking for permission directly");
            }
        } else {
            //permission is granted
            Log.v(LOG_TAG, "permission granted");
        }
    }

    private static void setCriteriaCoarse() {
        criteriaCoarse = new Criteria();
        criteriaCoarse.setAccuracy(Criteria.ACCURACY_COARSE);
        criteriaCoarse.setAltitudeRequired(false);
        criteriaCoarse.setBearingRequired(false);
        criteriaCoarse.setSpeedRequired(false);
    }

    private static void setCriteriaFine() {
        criteriaFine = new Criteria();
        criteriaFine.setAccuracy(Criteria.ACCURACY_FINE);
        criteriaFine.setAltitudeRequired(false);
        criteriaCoarse.setBearingRequired(false);
        criteriaCoarse.setSpeedRequired(false);
    }

    private void buildAlertMessage(int messageStringResource) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(messageStringResource)
                .setCancelable(false)
                .setPositiveButton(R.string.positivie_yes, new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton(R.string.negative_no, new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        alertDialog = builder.create();
        alertDialog.show();
    }

    private boolean isOutdated(Location location) {
        long currentTime = System.currentTimeMillis();
        return location != null ? currentTime - location.getTime() > 14400 * 1000 : false; // greater than 4 hours ( ms)
    }

}
