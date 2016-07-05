package com.daniloprado.weather.data.repository.impl;

import com.daniloprado.weather.data.repository.CityRepository;
import com.daniloprado.weather.model.City;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CityRepositoryImpl implements CityRepository {

    @Override
    public Observable<List<City>> getCities() {
        Observable.OnSubscribe<List<City>> onSubscribe = subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    List<City> cityList = new ArrayList<>();
                    for (int i = 1; i < 4; i++) {
                        City city = new City();
                        city.name = "City " + String.valueOf(i);
                        city.latitude = 1;
                        city.longitude = 1;
                        cityList.add(city);
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
