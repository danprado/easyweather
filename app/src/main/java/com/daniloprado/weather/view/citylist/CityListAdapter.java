package com.daniloprado.weather.view.citylist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daniloprado.weather.R;
import com.daniloprado.weather.model.City;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {

    private final CityClickListener cityClickListener;
    private List<City> cityList;

    CityListAdapter(List<City> cityList, CityClickListener cityClickListener) {
        this.cityList = cityList;
        this.cityClickListener = cityClickListener;
    }

    public void replaceDataSet(List<City> cityList) {
        this.cityList = cityList;
        notifyDataSetChanged();
    }

    public void delete(int position) {
        cityList.remove(position);
        notifyItemRemoved(position);
    }

    public City getItem(int position) {
        return cityList.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        City city = cityList.get(position);
        holder.cityName.setText(city.name);
        holder.contentLayout.setOnClickListener(l -> cityClickListener.onCityClick(city));
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    interface CityClickListener {
        void onCityClick(City city);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_container)
        View contentLayout;

        @BindView(R.id.textview_city_name)
        TextView cityName;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
