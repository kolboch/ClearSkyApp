package com.example.kb.clearsky;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.kb.clearsky.adapters.MyAdapterCities;
import com.example.kb.clearsky.adapters.MyAdapterCountries;
import com.example.kb.clearsky.location.LocationFetcher;
import com.example.kb.clearsky.model.database_specific.Country;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity implements LocationListener {

    private static final String LOG_TAG = SettingsActivity.class.getSimpleName();

    @BindView(R.id.toolbar_settings)
    Toolbar toolbar;
    @BindView(R.id.country_selection)
    AutoCompleteTextView countryTextView;
    @BindView(R.id.city_selection)
    AutoCompleteTextView cityTextView;
    @BindView(R.id.location_test)
    TextView locationResultTest;
    @BindView(R.id.location_button)
    Button locationButton;

    private MyAdapterCountries adapterCountries;
    private MyAdapterCities adapterCities;
    private Country selectedCountry;
    private LocationFetcher locationFetcher;
    private Location currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        selectedCountry = new Country();
        locationFetcher = new LocationFetcher(this, this.findViewById(android.R.id.content).getRootView());
        currentLocation = null;
        setMyToolbar();
        setAutoCompleteTextViews();
        setButtonsListeners();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LocationFetcher.REQUEST_CODE_PERM_LOCATIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateLocationTextView();
                } else {
                    final Snackbar snackbar = Snackbar.make(this.findViewById(android.R.id.content).getRootView(), R.string.permission_location_info, Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction(R.string.got_it, new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            snackbar.dismiss();
                        }
                    });
                    snackbar.show();
                }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationFetcher.dispose();
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.v(LOG_TAG, "GOT LOCATION !!!");
        this.currentLocation = location;
        updateLocationTextView();
        locationFetcher.removeUpdates();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        locationFetcher.updateLocation();
    }

    @Override
    public void onProviderDisabled(String s) {

    }

    private void setMyToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setAutoCompleteTextViews() {
        setCountriesAutoCompleteTextView();
        setCitiesAutoCompleteTextView();
    }

    private void setButtonsListeners() {
        this.locationButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(LOG_TAG, "Button location clicked.");
                locationFetcher.enableAskingForPermission();
                locationFetcher.updateLocation();
                updateLocationTextView();
            }
        });
    }

    private void setCountriesAutoCompleteTextView() {
        adapterCountries = new MyAdapterCountries(this);
        countryTextView.setAdapter(adapterCountries);
        countryTextView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Country c = (Country) adapterView.getItemAtPosition(i);
                selectedCountry.setCountryCode(c.getCountryCode());
                selectedCountry.setCountryName(c.getCountryName());
            }
        });
    }

    private void setCitiesAutoCompleteTextView() {
        adapterCities = new MyAdapterCities(this, selectedCountry);
        cityTextView.setAdapter(adapterCities);
        cityTextView.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    Log.v(LOG_TAG, "Country selected: " + selectedCountry);
                }
            }
        });
    }

    private void updateLocationTextView() {
        Location location = locationFetcher.getLocation();
        if (location != null && isNewerThanCurrent(location)) {
            currentLocation = location;
        }
        if (currentLocation != null) {
            DecimalFormat df = new DecimalFormat("#.00", DecimalFormatSymbols.getInstance(Locale.US));
            this.locationResultTest.setText(String.format("(%s, %s)",
                    df.format(currentLocation.getLatitude()), df.format(currentLocation.getLongitude())));
        }
    }

    private boolean isNewerThanCurrent(Location other) {
        return currentLocation != null ? other != null && other.getTime() > currentLocation.getTime() : true;
    }
}
