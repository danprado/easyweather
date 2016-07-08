package com.daniloprado.weather.view.citylist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by marcello on 07/07/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class CityListPresenterTest {

    CityListPresenter presenter;

    @Before
    public void setUp() {
        presenter = mock(CityListPresenter.class);
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void tryDeleteCityWithNullCityTest() {
        presenter.deleteCity(null);
    }

}
