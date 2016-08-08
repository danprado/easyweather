package com.daniloprado.weather.dagger;

import com.daniloprado.weather.dagger.module.ApplicationModule;
import com.daniloprado.weather.dagger.module.DbModule;
import com.daniloprado.weather.dagger.module.NetworkModule;
import com.daniloprado.weather.dagger.module.RepositoryModule;
import com.daniloprado.weather.dagger.module.ServiceModule;
import com.daniloprado.weather.view.cityadd.CityAddDialogFragment;
import com.daniloprado.weather.view.cityforecast.CityForecastFragment;
import com.daniloprado.weather.view.citylist.CityListFragment;
import com.daniloprado.weather.view.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        ServiceModule.class,
        RepositoryModule.class,
        DbModule.class
})
public interface DiComponent {

    void inject(MainActivity activity);

    void inject(CityListFragment fragment);

    void inject(CityForecastFragment fragment);

    void inject(CityAddDialogFragment fragment);

}
