package com.daniloprado.weather.view.citylist;

import com.daniloprado.weather.data.repository.CityRepository;
import com.daniloprado.weather.model.City;

import java.util.List;

import javax.inject.Inject;

public class CityListPresenter implements CityListContract.Presenter {

    private CityRepository cityRepository;
    private CityListContract.View view;
    private List<City> cityList;

    @Inject
    public CityListPresenter(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public void setView(CityListContract.View view) {
        this.view = view;
        loadData();
    }

    @Override
    public void loadData() {
        if (view != null) {
            view.showLoadingLayout();
            cityRepository.getCities().subscribe(cities -> {
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
            view.updateData(cityList);
            view.showContentLayout();
        }
    }

    @Override
    public void deleteCity(City city) {
        cityRepository.deleteCity(city);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

}
