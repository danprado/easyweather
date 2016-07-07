package com.daniloprado.weather.view.cityadd;

import android.app.Activity;
import android.content.Context;

import com.daniloprado.weather.data.db.DatabaseManager;
import com.daniloprado.weather.data.repository.CityRepository;
import com.daniloprado.weather.model.City;

import java.util.List;

import javax.inject.Inject;

public class CityAddPresenter implements CityAddContract.Presenter {

    private CityAddContract.View view;
    private CityRepository cityRepository;
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
        if (DatabaseManager.getInstance().checkCityExists(city.name)) {
            view.showErrorCityAlreadyExists();
        } else {
            DatabaseManager.getInstance().addCity(city);
            view.close(Activity.RESULT_OK);
        }
    }

    @Override
    public void searchCities(String query) {
        view.showLoadingLayout();
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

}
