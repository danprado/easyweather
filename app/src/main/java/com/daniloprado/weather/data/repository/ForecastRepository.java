package com.daniloprado.weather.data.repository;

import com.daniloprado.weather.data.dto.ForecastDto;

import rx.Observable;

public interface ForecastRepository {

    Observable<ForecastDto> getForecast(double latitude, double longitude);

}
