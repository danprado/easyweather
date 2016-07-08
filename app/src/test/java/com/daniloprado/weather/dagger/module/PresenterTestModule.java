package com.daniloprado.weather.dagger.module;

import com.daniloprado.weather.data.repository.CityRepository;
import com.daniloprado.weather.data.repository.ForecastRepository;
import com.daniloprado.weather.view.cityadd.CityAddContract;
import com.daniloprado.weather.view.cityadd.CityAddPresenter;
import com.daniloprado.weather.view.cityforecast.CityForecastContract;
import com.daniloprado.weather.view.cityforecast.CityForecastPresenter;
import com.daniloprado.weather.view.citylist.CityListContract;
import com.daniloprado.weather.view.citylist.CityListPresenter;

import org.mockito.Mockito;

public class PresenterTestModule extends PresenterModule {

    @Override
    public CityListContract.Presenter provideCityListPresenter(CityRepository cityRepository) {
        return Mockito.mock(CityListPresenter.class);
    }

    @Override
    public CityForecastContract.Presenter provideCityForecastPresenter(ForecastRepository forecastRepository) {
        return Mockito.mock(CityForecastPresenter.class);
    }

    @Override
    public CityAddContract.Presenter provideCityAddPresenter(CityRepository cityRepository) {
        return Mockito.mock(CityAddPresenter.class);
    }

}
