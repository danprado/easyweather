package com.daniloprado.weather.view.citylist;

import com.daniloprado.weather.model.City;

import java.util.List;

public interface CityListContract {

    interface View {

        void showLoadingLayout();

        void showContentLayout();

        void showErrorLayout();

        void updateData(List<City> cityList);

    }

    interface Presenter {

        void setView(View view);

        void loadData();

        void refreshUi();

        void deleteCity(City city);

        void onDestroy();

    }

}
