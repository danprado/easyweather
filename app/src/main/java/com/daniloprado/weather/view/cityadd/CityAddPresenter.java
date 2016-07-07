package com.daniloprado.weather.view.cityadd;

import android.content.Context;

import com.daniloprado.weather.data.db.DatabaseManager;
import com.daniloprado.weather.data.repository.CityRepository;
import com.daniloprado.weather.model.City;

import java.util.List;

import javax.inject.Inject;

public class CityAddPresenter implements CityAddContract.Presenter {

    private CityAddContract.View view;
    private CityRepository cityRepository;
    private City selectedCity;
    private List<City> cityList;

    @Inject
    public CityAddPresenter(CityRepository cityRepository, Context context) {
        this.cityRepository = cityRepository;
        DatabaseManager.init(context);
    }

    @Override
    public void setView(CityAddContract.View view) {
        this.view = view;
    }

    @Override
    public void onCitySelected(City city) {
        this.selectedCity = city;

        if (selectedCity != null) {
            view.setSelectedCity(city);
        }
    }

    @Override
    public void searchCities(String query) {
        cityRepository.searchCities(query).subscribe(cities -> {
            cityList = cities;
            refreshUi();
        }, throwable -> {
            throwable.printStackTrace();
            view.showErrorLayout();
        });
    }

    @Override
    public void refreshUi() {
        view.setupRecyclerViewAdapter(cityList);
        view.showContentLayout();
    }

    @Override
    public void saveCity() {
        if (selectedCity != null) {
            DatabaseManager.getInstance().addCity(selectedCity);
            view.close();
        } else {
            view.showErrorNoCitySelected();
        }
    }
}
