package com.daniloprado.weather.view.cityadd;

import android.app.Activity;

import com.daniloprado.weather.data.repository.CityRepository;
import com.daniloprado.weather.model.City;

import java.util.List;

import javax.inject.Inject;

public class CityAddPresenter implements CityAddContract.Presenter {

    private CityAddContract.View view;
    private CityRepository cityRepository;
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
    public void onCitySelected(City city) {
        if (view != null) {
            if (cityRepository.checkCityExists(city.name)) {
                view.showErrorCityAlreadyExists();
            } else {
                cityRepository.saveCity(city);
                view.close(Activity.RESULT_OK);
            }
        }
    }

    @Override
    public void searchCities(String query) {
        if (view != null) {
            view.showLoadingLayout();
            cityRepository.searchCities(query).subscribe(cities -> {
                cityList = cities;
                refreshUi();
            }, throwable -> {
                throwable.printStackTrace();
                view.showErrorLayout();
            });
        }
    }

    @Override
    public void refreshUi() {
        if (view != null) {
            view.setupRecyclerViewAdapter(cityList);
            view.showContentLayout();
        }
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
