package com.daniloprado.weather.view.citylist;

import android.content.Context;

import com.daniloprado.weather.data.db.DatabaseManager;
import com.daniloprado.weather.data.repository.CityRepository;
import com.daniloprado.weather.model.City;

import java.util.List;

import javax.inject.Inject;

public class CityListPresenter implements CityListContract.Presenter {

    private CityRepository cityRepository;
    private CityListContract.View view;
    private List<City> cityList;

    @Inject
    public CityListPresenter(CityRepository cityRepository, Context context) {
        this.cityRepository = cityRepository;
        DatabaseManager.init(context);
    }

    @Override
    public void setView(CityListContract.View view) {
        this.view = view;
        loadData();
    }

    @Override
    public void loadData() {
        view.showLoadingLayout();
        cityRepository.getCities().subscribe(cities -> {
            cityList = cities;
            refreshUi();
        }, throwable -> {
            throwable.printStackTrace();
            view.showErrorLayout();
        });
    }

    @Override
    public void refreshUi() {
        view.updateData(cityList);
        view.showContentLayout();
    }

    @Override
    public void deleteCity(City city) {
        DatabaseManager.getInstance().deleteCity(city);
    }
}
