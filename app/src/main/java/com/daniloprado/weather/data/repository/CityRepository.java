package com.daniloprado.weather.data.repository;

import com.daniloprado.weather.model.City;

import java.util.List;

import rx.Observable;

public interface CityRepository {

    void saveCity(City city);

    void deleteCity(City city);

    boolean checkCityExists(String name);

    Observable<List<City>> getCities();

    Observable<List<City>> searchCities(String query);

}
