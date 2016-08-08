package com.daniloprado.weather.view.cityforecast;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.daniloprado.weather.R;
import com.daniloprado.weather.data.dto.ForecastDto;
import com.daniloprado.weather.model.City;
import com.daniloprado.weather.model.weather.Data;
import com.daniloprado.weather.util.DateUtils;
import com.daniloprado.weather.util.ViewFlipperUtil;
import com.daniloprado.weather.util.WeatherUtils;
import com.daniloprado.weather.view.base.BaseFragment;
import com.daniloprado.weather.widget.DailyWeatherView;

import javax.inject.Inject;

import butterknife.BindView;

public class CityForecastFragment extends BaseFragment implements CityForecastContract.View {

    @BindView(R.id.textview_temperature)
    TextView textViewTemperature;

    @BindView(R.id.textview_city_name)
    TextView textViewCityName;

    @BindView(R.id.textview_current_city_weather)
    TextView textViewCurrentWeather;

    @BindView(R.id.imageview_current_day_weather)
    ImageView imageViewCurrentWeather;

    @BindView(R.id.first_day)
    DailyWeatherView dailyWeatherViewDayOne;

    @BindView(R.id.second_day)
    DailyWeatherView dailyWeatherViewDayTwo;

    @BindView(R.id.third_day)
    DailyWeatherView dailyWeatherViewDayThree;

    @BindView(R.id.fourth_day)
    DailyWeatherView dailyWeatherViewDayFour;

    @BindView(R.id.fifth_day)
    DailyWeatherView dailyWeatherViewDayFive;

    @BindView(R.id.error_layout)
    LinearLayout errorLayout;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.viewflipper)
    ViewFlipper viewFlipper;

    @Inject
    CityForecastPresenter presenter;

    private City city = new City();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDiComponent().inject(this);
        loadArgs();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city_forecast, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setView(this);
        presenter.loadDataWithProgress(city);
        setupUi();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupUi() {
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.loadData(city));
        swipeRefreshLayout.setColorSchemeColors(ResourcesCompat.getColor(getResources(), R.color.colorAccent, null));
    }

    private void loadArgs() {
        Bundle bundle = getArguments();
        if (bundle.containsKey(City.KEY)) {
            city = (City) bundle.getSerializable(City.KEY);
        }
    }

    @Override
    public void showLoadingLayout() {
        ViewFlipperUtil.setDisplayedChild(viewFlipper, progressBar);
    }

    @Override
    public void showContentLayout() {
        swipeRefreshLayout.setRefreshing(false);
        ViewFlipperUtil.setDisplayedChild(viewFlipper, swipeRefreshLayout);
    }

    @Override
    public void showErrorLayout() {
        ViewFlipperUtil.setDisplayedChild(viewFlipper, errorLayout);
    }

    @Override
    public void updateForecast(ForecastDto dto) {
        textViewTemperature.setText(String.valueOf(WeatherUtils.getFormattedTemperature(dto.currently.temperature)));
        textViewCityName.setText(city.name);
        textViewCurrentWeather.setText(dto.currently.summary);
        imageViewCurrentWeather.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                WeatherUtils.getWeatherIconResourceFromString(dto.currently.icon),
                null));

        setupDayForecast(dailyWeatherViewDayOne, dto.daily.data.get(1));
        setupDayForecast(dailyWeatherViewDayTwo, dto.daily.data.get(2));
        setupDayForecast(dailyWeatherViewDayThree, dto.daily.data.get(3));
        setupDayForecast(dailyWeatherViewDayFour, dto.daily.data.get(4));
        setupDayForecast(dailyWeatherViewDayFive, dto.daily.data.get(5));
    }

    private void setupDayForecast(DailyWeatherView dailyWeatherView, Data data) {
        dailyWeatherView.setDayOfTheWeek(DateUtils.getDayOfTheWeekFromUnixTime(data.time));
        dailyWeatherView.setDayWeatherImage(WeatherUtils.getWeatherIconResourceFromString(data.icon));
        dailyWeatherView.setMaxDayTemp(WeatherUtils.getFormattedTemperature(data.temperatureMax));
        dailyWeatherView.setMinDayTemp(WeatherUtils.getFormattedTemperature(data.temperatureMin));
    }

}
