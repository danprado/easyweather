package com.daniloprado.weather.data.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.daniloprado.weather.model.City;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "weather";
    private static final int DATABASE_VERSION = 1;

    private Dao<City, Integer> cityDao = null;
    private List<City> cities;

    @Inject
    public DatabaseHelper(Context context, @Named("DefaultCities") List<City> cities) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.cities = cities;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, City.class);
            initCities(getCityDao(), cities);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to create database", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, City.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void initCities(Dao<City, Integer> cityDao, List<City> cities) {
        for (City city : cities) {
            try {
                cityDao.create(city);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Dao<City, Integer> getCityDao() throws SQLException {
        if (cityDao == null) {
            cityDao = getDao(City.class);
        }
        return cityDao;
    }

}
