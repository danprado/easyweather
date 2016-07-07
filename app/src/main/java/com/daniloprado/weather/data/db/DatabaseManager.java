package com.daniloprado.weather.data.db;

import android.content.Context;

import com.daniloprado.weather.data.db.helper.DatabaseHelper;
import com.daniloprado.weather.model.City;

import java.sql.SQLException;
import java.util.List;

public class DatabaseManager {

    static private DatabaseManager instance;
    private DatabaseHelper helper;

    private DatabaseManager(Context context) {
        helper = new DatabaseHelper(context);
    }

    static public void init(Context context) {
        if (null == instance) {
            instance = new DatabaseManager(context);
        }
    }

    static public DatabaseManager getInstance() {
        return instance;
    }

    private DatabaseHelper getHelper() {
        return helper;
    }

    public List<City> getAllCities() {
        List<City> cityList = null;
        try {
            cityList = getHelper().getCityDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityList;
    }

    public void addCity(City city) {
        try {
            getHelper().getCityDao().create(city);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCity(City city) {
        try {
            getHelper().getCityDao().delete(city);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkCityExists(String name) {
        try {
            if (getHelper().getCityDao().queryForEq("name", name).size() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

}
