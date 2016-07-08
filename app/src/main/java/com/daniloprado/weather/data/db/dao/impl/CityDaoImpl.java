package com.daniloprado.weather.data.db.dao.impl;

import com.daniloprado.weather.data.db.dao.CityDao;
import com.daniloprado.weather.model.City;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class CityDaoImpl extends BaseDaoImpl<City, Integer> implements CityDao {

    public CityDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, City.class);
    }

}
