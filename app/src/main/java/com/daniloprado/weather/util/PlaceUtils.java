package com.daniloprado.weather.util;

import android.location.Address;

public abstract class PlaceUtils {

    public static String getCityDescription(Address address) {
        String description = address.getLocality();
        if (address.getAdminArea() != null) {
            description += ", " + address.getAdminArea();
        }

        if (address.getCountryName() != null) {
            description += ", " + address.getCountryName();
        }
        return  description;
    }

}
