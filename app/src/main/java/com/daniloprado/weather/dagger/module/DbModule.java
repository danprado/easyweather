package com.daniloprado.weather.dagger.module;

import android.content.Context;

import com.daniloprado.weather.data.db.helper.DatabaseHelper;
import com.daniloprado.weather.model.City;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    @Provides
    @Singleton
    DatabaseHelper provideDatabaseHelper(Context context, @Named("DefaultCities") List<City> cities) {
        return new DatabaseHelper(context, cities);
    }

    @Provides
    @Singleton
    ConnectionSource provideConnectionSource(DatabaseHelper databaseHelper) {
        return databaseHelper.getConnectionSource();
    }

    @Provides
    @Singleton
    @Named("CityDao")
    RuntimeExceptionDao<City, Integer> provideCityDao(DatabaseHelper databaseHelper) {
        return databaseHelper.getRuntimeExceptionDao(City.class);
    }

    @Provides
    @Singleton
    @Named("DefaultCities")
    List<City> provideDefaultCities() {
        List<City> cities = new ArrayList<>(4);

        City dublin = new City();
        dublin.name = "Dublin";
        dublin.fullDescription = "Dublin, Ireland";
        dublin.latitude = 53.350140;
        dublin.longitude = -6.266155;
        cities.add(dublin);

        City london = new City();
        london.name = "London";
        london.fullDescription = "London, UK";
        london.latitude = 51.508530;
        london.longitude = -0.076132;
        cities.add(london);

        City newYork = new City();
        newYork.name = "New York";
        newYork.fullDescription = "New York, US";
        newYork.latitude = 40.730610;
        newYork.longitude = -73.935242;
        cities.add(newYork);

        City barcelona = new City();
        barcelona.name = "Barcelona";
        barcelona.fullDescription = "Barcelona, Spain";
        barcelona.latitude = 41.390205;
        barcelona.longitude = 2.154007;
        cities.add(barcelona);

        return cities;
    }

}
