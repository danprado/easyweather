package com.daniloprado.weather.data.repository.impl;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.daniloprado.weather.data.repository.CityRepository;
import com.daniloprado.weather.model.City;
import com.daniloprado.weather.util.PlaceUtils;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Singleton
public class CityRepositoryImpl implements CityRepository {

    private Context context;
    private RuntimeExceptionDao<City, Integer> cityDao;

    @Inject
    public CityRepositoryImpl(Context context, @Named("CityDao") RuntimeExceptionDao<City, Integer> cityDao) {
        this.context = context;
        this.cityDao = cityDao;
    }

    @Override
    public void saveCity(City city) {
        cityDao.create(city);
    }

    @Override
    public void deleteCity(City city) {
        cityDao.delete(city);
    }

    @Override
    public boolean checkCityExists(String name) {
        return cityDao.queryForEq("name", name).size() > 0;
    }

    @Override
    public Observable<List<City>> getCities() {
        Observable.OnSubscribe<List<City>> onSubscribe = subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(cityDao.queryForAll());
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        };
        return Observable.create(onSubscribe)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<City>> searchCities(String query) {
        Observable.OnSubscribe<List<City>> onSubscribe = subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    List<City> cityList = new ArrayList<>();

                    Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                    List<Address> addresses = geocoder.getFromLocationName(query, 5);

                    for (Address address : addresses) {
                        if (address.getLocality() != null
                                && address.hasLatitude()
                                && address.hasLongitude()) {
                            City city = new City();
                            city.name = address.getLocality();
                            city.fullDescription = PlaceUtils.getCityDescription(address);
                            city.latitude = address.getLatitude();
                            city.longitude = address.getLongitude();

                            if (!cityList.contains(city)) {
                                cityList.add(city);
                            }
                        }
                    }

                    subscriber.onNext(cityList);
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        };
        return Observable.create(onSubscribe)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
