package com.daniloprado.weather.dagger.module;

import com.daniloprado.weather.data.db.dao.CityDao;
import com.daniloprado.weather.data.db.dao.impl.CityDaoImpl;
import com.daniloprado.weather.data.db.helper.DatabaseHelper;
import com.daniloprado.weather.model.City;
import com.j256.ormlite.support.ConnectionSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    private DatabaseHelper databaseHelper;

    public DbModule(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Provides
    @Singleton
    DatabaseHelper provideDatabaseHelper() {
        return databaseHelper;
    }

    @Provides
    @Singleton
    ConnectionSource provideConnectionSource() {
        return databaseHelper.getConnectionSource();
    }

//    @Provides
//    @Singleton
//    CityDao provideCityDao() {
//        return databaseHelper.getRuntimeExceptionDao(City.class);
//    }

}
