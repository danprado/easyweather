package com.daniloprado.weather.view.cityforecast;

import com.daniloprado.weather.data.dto.ForecastDto;
import com.daniloprado.weather.model.City;

public interface CityForecastContract {

    interface View {

        void showLoadingLayout();

        void showContentLayout();

        void showErrorLayout();

        void updateForecast(ForecastDto dto);

    }

    interface Presenter {

        void setView(CityForecastContract.View view);

        void loadData(City city);

        void loadDataWithProgress(City city);

        void refreshUi();

        void onDestroy();

    }

}
