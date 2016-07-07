package com.daniloprado.weather.view.cityforecast;

import com.daniloprado.weather.data.dto.ForecastDto;
import com.daniloprado.weather.data.repository.ForecastRepository;
import com.daniloprado.weather.model.City;

import javax.inject.Inject;

public class CityForecastPresenter implements CityForecastContract.Presenter {

    private ForecastRepository forecastRepository;
    private ForecastDto forecastDto;
    private CityForecastContract.View view;

    @Inject
    public CityForecastPresenter(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    @Override
    public void setView(CityForecastContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData(City city) {
        forecastRepository.getForecast(city.latitude, city.longitude).subscribe(dto -> {
            forecastDto = dto;
            refreshUi();
        }, throwable -> {
            throwable.printStackTrace();
            view.showErrorLayout();
        });
    }

    @Override
    public void loadDataWithProgress(City city) {
        view.showLoadingLayout();
        loadData(city);
    }

    @Override
    public void refreshUi() {
        view.updateForecast(forecastDto);
        view.showContentLayout();
    }

}
