package com.daniloprado.weather.view.cityadd;

import com.daniloprado.weather.data.repository.CityRepository;
import com.daniloprado.weather.model.City;

import javax.inject.Inject;

public class CityAddPresenter implements CityAddContract.Presenter {

    private CityAddContract.View view;
    private City city;
    private CityRepository cityRepository;

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
        this.city = city;
    }

    @Override
    public void saveCity() {

    }
}
