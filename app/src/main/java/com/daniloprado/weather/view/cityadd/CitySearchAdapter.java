package com.daniloprado.weather.view.cityadd;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daniloprado.weather.R;
import com.daniloprado.weather.model.City;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CitySearchAdapter extends RecyclerView.Adapter<CitySearchAdapter.ViewHolder> {

    private final List<City> cityList;
    private final CityClickListener cityClickListener;

    CitySearchAdapter(List<City> cityList, CityClickListener cityClickListener) {
        this.cityList = cityList;
        this.cityClickListener = cityClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_city_add, parent, false);
        return new CitySearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        City city = cityList.get(position);
        holder.cityName.setText(city.fullDescription);
        holder.linearLayout.setOnClickListener(l -> cityClickListener.onCityClick(city));
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    interface CityClickListener {
        void onCityClick(City city);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.content_layout_found_city)
        LinearLayout linearLayout;

        @BindView(R.id.textview_found_city_name)
        TextView cityName;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

}
