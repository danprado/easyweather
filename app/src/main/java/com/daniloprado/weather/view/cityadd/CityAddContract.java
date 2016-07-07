package com.daniloprado.weather.view.cityadd;

import com.daniloprado.weather.model.City;

import java.util.List;

public interface CityAddContract {

    interface View {

        void close();
        void showErrorNoCitySelected();
        void showErrorLayout();
        void showContentLayout();
        void setSelectedCity(City city);
        void setupRecyclerViewAdapter(List<City> cityList);

    }

    interface Presenter {

        void setView(CityAddContract.View view);
        void onCitySelected(City city);
        void searchCities(String query);
        void refreshUi();
        void saveCity();

    }

}
