package com.daniloprado.weather.view.cityadd;

import com.daniloprado.weather.model.City;

public interface CityAddContract {

    interface View {

        void close();
        void showErrorNoCitySelected();

    }

    interface Presenter {

        void setView(CityAddContract.View view);
        void setSelectedCity(City city);
        void saveCity();

    }

}
