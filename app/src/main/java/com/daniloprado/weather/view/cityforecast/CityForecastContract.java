package com.daniloprado.weather.view.cityforecast;

import com.daniloprado.weather.data.dto.ForecastDto;
import com.daniloprado.weather.model.City;

public interface CityForecastContract {

    interface View {

        void showLoadingLayout();
        void showContentLayout();
        void showErrorLayout();
        void setupUi(ForecastDto dto);

    }

    interface Presenter {

        void setView(CityForecastContract.View view);
        void loadData(City city);
        void refreshUi();

    }

}
