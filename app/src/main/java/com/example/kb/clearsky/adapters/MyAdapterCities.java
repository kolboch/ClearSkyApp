package com.example.kb.clearsky.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.kb.clearsky.db_handlers.DbAccess;
import com.example.kb.clearsky.model.database_specific.City;
import com.example.kb.clearsky.model.database_specific.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karol on 2017-07-13.
 */

public class MyAdapterCities extends BaseAdapter implements Filterable {

    private static final int VIEW_TYPE_COUNT = 1;
    private List<City> cities;
    private List<City> citiesFiltered;
    private Context context;
    private CitiesFilter filter;
    private Country selectedCountry;

    public MyAdapterCities(Context context, Country selectedCountry) {
        this.context = context;
        this.selectedCountry = selectedCountry;
        this.cities = new ArrayList<>();
        this.citiesFiltered = this.cities;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int i) {
        City city;
        try {
            city = cities.get(i);
        } catch (IndexOutOfBoundsException e) {
            city = null;
        }
        return city;
    }

    @Override
    public long getItemId(int i) {
        City c = (City) getItem(i);
        return c != null ? c.getCityID() : 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        }
        City c = cities.get(i);
        ((TextView) view.findViewById(android.R.id.text1)).setText(c.toString());
        return view;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CitiesFilter();
        }
        return filter;
    }

    private void initCities() {
        DbAccess dbAccess = DbAccess.getInstance(context);
        dbAccess.open();
        this.cities = dbAccess.getCitiesWithinCountry(selectedCountry);
        dbAccess.close();
        this.citiesFiltered = this.cities;
    }

    private class CitiesFilter extends Filter {

        private static final int THRESHOLD = 2;
        private Country filterCountry;

        public CitiesFilter() {
            this.filterCountry = new Country();
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            if (charSequence != null && charSequence.length() >= THRESHOLD
                    && selectedCountry != null && !TextUtils.isEmpty(selectedCountry.getCountryCode())) {
                initCitiesIfCountryChanged();
                List<City> filtered = new ArrayList<>();
                for (City city : citiesFiltered) {
                    if (city.getCityName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filtered.add(new City(city));
                    }
                }
                results.values = filtered;
                results.count = filtered.size();
            } else {
                results.values = citiesFiltered;
                results.count = citiesFiltered.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if (filterResults.count == 0) {
                notifyDataSetInvalidated();
            } else {
                cities = (List<City>) filterResults.values;
                notifyDataSetChanged();
            }
        }

        private void initCitiesIfCountryChanged(){
            if (!filterCountry.equals(selectedCountry)) {
                filterCountry = new Country(selectedCountry);
                initCities();
            }
        }
    }
}
