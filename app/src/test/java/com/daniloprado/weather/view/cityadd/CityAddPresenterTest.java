package com.daniloprado.weather.view.cityadd;

import com.daniloprado.weather.data.repository.CityRepository;
import com.daniloprado.weather.model.City;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityAddPresenterTest {

    @Mock
    CityRepository cityRepository;

    @Mock
    CityAddContract.View view;

    private CityAddPresenter cityAddPresenter;
    private City addedCity;

    @Before
    public void setUp() {
        cityAddPresenter = new CityAddPresenter(cityRepository);
        cityAddPresenter.setView(view);
    }

    @Test
    public void shouldNotAddCityIfCityExits() {
        City city = new City();
        city.name = "Dublin";
        when(cityRepository.checkCityExists("Dublin")).thenReturn(true);
        doAnswer(invocation -> {
            addedCity = (City) invocation.getArguments()[0];
            return addedCity;
        }).when(cityRepository).saveCity(city);
        cityAddPresenter.onCitySelected(city);
        Assert.assertNull(addedCity);
        Assert.assertNotEquals(city, addedCity);
    }

    @Test
    public void shouldAddCityIfCityNotExists() {
        City city = new City();
        city.name = "Dublin";
        when(cityRepository.checkCityExists("Dublin")).thenReturn(false);
        doAnswer(invocation -> {
            addedCity = (City) invocation.getArguments()[0];
            return addedCity;
        }).when(cityRepository).saveCity(city);
        cityAddPresenter.onCitySelected(city);
        Assert.assertEquals(city, addedCity);
    }

}
