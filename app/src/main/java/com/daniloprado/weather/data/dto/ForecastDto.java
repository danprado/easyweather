package com.daniloprado.weather.data.dto;

import com.daniloprado.weather.model.weather.Currently;
import com.daniloprado.weather.model.weather.Daily;

import java.io.Serializable;

public class ForecastDto implements Serializable {

    public double latitude;
    public double longitude;
    public String timezone;
    public Double offset;
    public Currently currently;
    public Daily daily;

}
