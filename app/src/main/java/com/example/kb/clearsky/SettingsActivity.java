package com.example.kb.clearsky;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.AutoCompleteTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_settings)
    Toolbar toolbar;
    @BindView(R.id.country_selection)
    AutoCompleteTextView countryTextView;
    @BindView(R.id.city_selection)
    AutoCompleteTextView cityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        setMyToolbar();
        setAutoCompleteTextViews();
    }

    private void setMyToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setAutoCompleteTextViews(){
        final MyAdapterCountries countriesAdapter = new MyAdapterCountries(this);
        countryTextView.setAdapter(countriesAdapter);
    }
}
