package com.daniloprado.weather.model;

import com.daniloprado.weather.model.base.BaseModel;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class City extends BaseModel {

    public static String KEY = "city";

    @DatabaseField(generatedId = true)
    public int id;

    @DatabaseField
    public String name;

    @DatabaseField
    public String fullDescription;

    @DatabaseField
    public double latitude;

    @DatabaseField
    public double longitude;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        return fullDescription != null ? fullDescription.equals(city.fullDescription) : city.fullDescription == null;

    }

    @Override
    public int hashCode() {
        return fullDescription != null ? fullDescription.hashCode() : 0;
    }

}
