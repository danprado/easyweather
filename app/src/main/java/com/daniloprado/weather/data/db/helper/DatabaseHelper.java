package com.daniloprado.weather.data.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.daniloprado.weather.model.City;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import javax.inject.Inject;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "weather";
    private static final int DATABASE_VERSION = 1;

    private Dao<City, Integer> cityDao = null;

    @Inject
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, City.class);
            initDefaultData();
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

    private void initDefaultData() {
        City dublin = new City();
        dublin.name = "Dublin";
        dublin.fullDescription = "Dublin, Ireland";
        dublin.latitude = 53.350140;
        dublin.longitude = -6.266155;

        City london = new City();
        london.name = "London";
        london.fullDescription = "London, UK";
        london.latitude = 51.508530;
        london.longitude = -0.076132;

        City newYork = new City();
        newYork.name = "New York";
        newYork.fullDescription = "New York, US";
        newYork.latitude = 40.730610;
        newYork.longitude = -73.935242;

        City barcelona = new City();
        barcelona.name = "Barcelona";
        barcelona.fullDescription = "Barcelona, Spain";
        barcelona.latitude = 41.390205;
        barcelona.longitude = 2.154007;

        try {
            getCityDao().create(dublin);
            getCityDao().create(london);
            getCityDao().create(newYork);
            getCityDao().create(barcelona);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<City, Integer> getCityDao() throws SQLException {
        if (cityDao == null) {
            cityDao = getDao(City.class);
        }
        return cityDao;
    }

}
