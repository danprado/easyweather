package com.daniloprado.weather.view.cityadd;

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
    public CityAddPresenter(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public void setView(CityAddContract.View view) {
        this.view = view;
    }

    @Override
    public void setSelectedCity(City city) {
        this.selectedCity = city;
    }

    @Override
    public void searchCities(String query) {
        cityList.clear();
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
        if (cityList != null && cityList.isEmpty()) {
            view.showEmptyLayout();
        } else {
            view.setupRecyclerViewAdapter(cityList);
            view.showContentLayout();
        }
    }

    @Override
    public void saveCity() {

    }
}
