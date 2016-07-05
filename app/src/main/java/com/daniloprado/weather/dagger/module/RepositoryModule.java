package com.daniloprado.weather.dagger.module;

import android.content.Context;

import com.daniloprado.weather.MainApplication;
import com.daniloprado.weather.dagger.ForApplication;
import com.daniloprado.weather.data.repository.CityRepository;
import com.daniloprado.weather.data.repository.ForecastRepository;
import com.daniloprado.weather.data.repository.impl.CityRepositoryImpl;
import com.daniloprado.weather.data.repository.impl.ForecastRepositoryImpl;
import com.daniloprado.weather.data.service.ForecastService;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    CityRepository provideCityRepository() {
        return new CityRepositoryImpl();
    }

    @Provides
    ForecastRepository provideForecastRepository(ForecastService service, Context context) {
        return new ForecastRepositoryImpl(service, context);
    }

}
