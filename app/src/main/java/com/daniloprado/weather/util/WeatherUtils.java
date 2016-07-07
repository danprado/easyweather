package com.daniloprado.weather.util;

import com.daniloprado.weather.R;

public final class WeatherUtils {

    private WeatherUtils() {
    }

    public static String getFormattedTemperature(Double temp) {
        return String.valueOf(temp.intValue()) + "º";
    }

    public static int getWeatherIconResourceFromString(String string) {

        switch (string) {
            case "clear-day": {
                return R.drawable.ic_sun;
            }

            case "clear-night": {
                return R.drawable.ic_moon;
            }

            case "rain": {
                return R.drawable.ic_rain;
            }

            case "snow": {
                return R.drawable.ic_snow;
            }

            case "sleet": {
                return R.drawable.ic_sleet;
            }

            case "wind": {
                return R.drawable.ic_windy;
            }

            case "fog": {
                return R.drawable.ic_foggy;
            }

            case "cloudy": {
                return R.drawable.ic_clouds;
            }

            case "partly-cloudy-day": {
                return R.drawable.ic_partly_cloudy_day;
            }

            case "partly-cloudy-night": {
                return R.drawable.ic_partly_cloudy_night;
            }

            default: {
                return R.drawable.ic_sun;
            }
        }

    }

}
