package com.daniloprado.weather.view.cityforecast;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daniloprado.weather.R;
import com.daniloprado.weather.data.dto.ForecastDto;
import com.daniloprado.weather.model.City;
import com.daniloprado.weather.util.DateUtils;
import com.daniloprado.weather.util.WeatherUtils;
import com.daniloprado.weather.view.base.ContractFragment;
import com.daniloprado.weather.widget.DailyWeatherView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityForecastFragment extends ContractFragment<CityForecastFragment.Contract> implements CityForecastContract.View {

    public interface Contract {
        void onBackPressed();
    }

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

    @Inject CityForecastContract.Presenter presenter;
    private City city;

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
        ButterKnife.bind(this, view);
        presenter.setView(this);
        presenter.loadData(city);
    }

    public void loadArgs() {
        Bundle bundle = getArguments();
        city = (City) bundle.getSerializable("city");
    }

    @Override
    public void showLoadingLayout() {

    }

    @Override
    public void showContentLayout() {

    }

    @Override
    public void showErrorLayout() {

    }

    @Override
    public void setupUi(ForecastDto dto) {
        textViewTemperature.setText(String.valueOf(WeatherUtils.getFormattedTemperature(dto.currently.temperature)));
        textViewCityName.setText(city.name);
        textViewCurrentWeather.setText(dto.currently.summary);
        imageViewCurrentWeather.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                WeatherUtils.getWeatherIconResourceFromString(dto.currently.icon),
                null));

        dailyWeatherViewDayOne.setDayOfTheWeek(DateUtils.getDayOfTheWeekFromUnixTime(dto.daily.data.get(1).time));
        dailyWeatherViewDayOne.setDayWeather(dto.daily.data.get(1).icon);
        dailyWeatherViewDayOne.setMaxDayTemp(WeatherUtils.getFormattedTemperature(dto.daily.data.get(1).temperatureMax));
        dailyWeatherViewDayOne.setMinDayTemp(WeatherUtils.getFormattedTemperature(dto.daily.data.get(1).temperatureMin));

        dailyWeatherViewDayOne.setDayOfTheWeek(DateUtils.getDayOfTheWeekFromUnixTime(dto.daily.data.get(2).time));
        dailyWeatherViewDayTwo.setDayWeather(dto.daily.data.get(2).icon);
        dailyWeatherViewDayTwo.setMaxDayTemp(WeatherUtils.getFormattedTemperature(dto.daily.data.get(2).temperatureMax));
        dailyWeatherViewDayTwo.setMinDayTemp(WeatherUtils.getFormattedTemperature(dto.daily.data.get(2).temperatureMin));

        dailyWeatherViewDayOne.setDayOfTheWeek(DateUtils.getDayOfTheWeekFromUnixTime(dto.daily.data.get(3).time));
        dailyWeatherViewDayThree.setDayWeather(dto.daily.data.get(3).icon);
        dailyWeatherViewDayThree.setMaxDayTemp(WeatherUtils.getFormattedTemperature(dto.daily.data.get(3).temperatureMax));
        dailyWeatherViewDayThree.setMinDayTemp(WeatherUtils.getFormattedTemperature(dto.daily.data.get(3).temperatureMin));

        dailyWeatherViewDayOne.setDayOfTheWeek(DateUtils.getDayOfTheWeekFromUnixTime(dto.daily.data.get(4).time));
        dailyWeatherViewDayFour.setDayWeather(dto.daily.data.get(4).icon);
        dailyWeatherViewDayFour.setMaxDayTemp(WeatherUtils.getFormattedTemperature(dto.daily.data.get(4).temperatureMax));
        dailyWeatherViewDayFour.setMinDayTemp(WeatherUtils.getFormattedTemperature(dto.daily.data.get(4).temperatureMin));

        dailyWeatherViewDayOne.setDayOfTheWeek(DateUtils.getDayOfTheWeekFromUnixTime(dto.daily.data.get(5).time));
        dailyWeatherViewDayFive.setDayWeather(dto.daily.data.get(5).icon);
        dailyWeatherViewDayFive.setMaxDayTemp(WeatherUtils.getFormattedTemperature(dto.daily.data.get(5).temperatureMax));
        dailyWeatherViewDayFive.setMinDayTemp(WeatherUtils.getFormattedTemperature(dto.daily.data.get(5).temperatureMin));
    }

}
