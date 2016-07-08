package com.daniloprado.weather.dagger;

import com.daniloprado.weather.dagger.module.PresenterTestModule;
import com.daniloprado.weather.view.citylist.CityListPresenterTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PresenterTestModule.class})
public interface TestComponent {

    void inject(CityListPresenterTest target);

}
