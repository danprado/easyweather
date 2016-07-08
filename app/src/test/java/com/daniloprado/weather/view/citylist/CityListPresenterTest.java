package com.daniloprado.weather.view.citylist;

import android.content.Context;

import com.daniloprado.weather.data.db.helper.DatabaseHelper;
import com.daniloprado.weather.data.repository.CityRepository;
import com.daniloprado.weather.data.repository.impl.CityRepositoryImpl;
import com.daniloprado.weather.model.City;
import com.daniloprado.weather.view.cityadd.CityAddPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import rx.observers.TestSubscriber;

@RunWith(MockitoJUnitRunner.class)
public class CityListPresenterTest {

    private DatabaseHelper db;

    @Mock
    CityRepository cityRepository;

    private CityAddPresenter cityAddPresenter;
    private List<City> cityList = new ArrayList<>();

    @Before
    public void setUp() {
        cityAddPresenter = new CityAddPresenter(cityRepository);
    }

    @Test
    public void shouldNotAddTheSameCityTwice() {
        City city = new City();
        city.name = "Dublin";

        when(cityRepository.checkCityExists("Dublin")).thenReturn(false);
        cityAddPresenter.onCitySelected(city);
        verify(cityRepository).saveCity(city);
    }

}
