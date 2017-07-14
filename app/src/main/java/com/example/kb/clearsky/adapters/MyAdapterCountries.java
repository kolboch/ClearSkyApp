package com.example.kb.clearsky.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.kb.clearsky.db_handlers.DbAccess;
import com.example.kb.clearsky.model.database_specific.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karol on 2017-07-11.
 */

public class MyAdapterCountries extends BaseAdapter implements Filterable {

    private static final int VIEW_TYPE_COUNT = 1;
    private List<Country> countries;
    private List<Country> countriesFiltered;
    private Context context;
    private CountriesFilter filter;

    public MyAdapterCountries(Context context) {
        this.context = context;
        initCountries();
        this.countriesFiltered = this.countries;
    }

    private void initCountries() {
        DbAccess db = DbAccess.getInstance(context);
        db.open();
        this.countries = db.getCountries();
        db.close();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int i) {
        return countries.get(i);
    }

    @Override
    public long getItemId(int i) {
        //useless, but there is no unique numeric identifier in country
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        }
        Country c = countries.get(i);
        ((TextView) view.findViewById(android.R.id.text1)).setText(c.toString());
        return view;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public boolean isEmpty() {
        return countries.size() == 0;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CountriesFilter();
        }
        return filter;
    }

    private class CountriesFilter extends Filter {
        private static final int THRESHOLD = 2;

        public CountriesFilter() {
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            if (charSequence != null && charSequence.length() >= THRESHOLD) {
                List<Country> filtered = new ArrayList<>();
                for (Country country : countriesFiltered) {
                    if (country.getCountryName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filtered.add(new Country(country.getCountryName(), country.getCountryCode()));
                    }
                }
                results.values = filtered;
                results.count = filtered.size();
            } else {
                results.values = countriesFiltered;
                results.count = countriesFiltered.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if (filterResults.count == 0) {
                notifyDataSetInvalidated();
            } else {
                countries = (List<Country>) filterResults.values;
                notifyDataSetChanged();
            }
        }
    }
}
