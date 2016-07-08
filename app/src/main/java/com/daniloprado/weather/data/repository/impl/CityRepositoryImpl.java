package com.daniloprado.weather.data.repository.impl;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.daniloprado.weather.data.db.helper.DatabaseHelper;
import com.daniloprado.weather.data.repository.CityRepository;
import com.daniloprado.weather.model.City;
import com.daniloprado.weather.util.PlaceUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CityRepositoryImpl implements CityRepository {

    private Context context;
    private DatabaseHelper databaseHelper;

    @Inject
    public CityRepositoryImpl(Context context, DatabaseHelper databaseHelper) {
        this.context = context;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public void saveCity(City city) {
        try {
            databaseHelper.getCityDao().create(city);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCity(City city) {
        try {
            databaseHelper.getCityDao().delete(city);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkCityExists(String name) {
        try {
            if (databaseHelper.getCityDao().queryForEq("name", name).size() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    @Override
    public Observable<List<City>> getCities() {
        Observable.OnSubscribe<List<City>> onSubscribe = subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(databaseHelper.getCityDao().queryForAll());
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
