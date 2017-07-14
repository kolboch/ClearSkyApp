package com.example.kb.clearsky;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;

import com.example.kb.clearsky.adapters.MyAdapterCities;
import com.example.kb.clearsky.adapters.MyAdapterCountries;
import com.example.kb.clearsky.model.database_specific.Country;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    private static final String LOG_TAG = SettingsActivity.class.getSimpleName();

    @BindView(R.id.toolbar_settings)
    Toolbar toolbar;
    @BindView(R.id.country_selection)
    AutoCompleteTextView countryTextView;
    @BindView(R.id.city_selection)
    AutoCompleteTextView cityTextView;

    private MyAdapterCountries adapterCountries;
    private MyAdapterCities adapterCities;
    private Country selectedCountry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        selectedCountry = new Country();
        setMyToolbar();
        setAutoCompleteTextViews();
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

    private void setCountriesAutoCompleteTextView(){
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

    private void setCitiesAutoCompleteTextView(){
        adapterCities = new MyAdapterCities(this, selectedCountry);
        cityTextView.setAdapter(adapterCities);
        cityTextView.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    Log.v(LOG_TAG, "Country selected: " + selectedCountry);
                }
            }
        });
    }
}
