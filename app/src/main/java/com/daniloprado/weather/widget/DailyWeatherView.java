package com.daniloprado.weather.widget;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.daniloprado.weather.R;

public class DailyWeatherView extends FrameLayout {

    private TextView textViewDayOfTheWeek;
    private ImageView imageViewDayWeather;
    private TextView textViewDayMaxTemp;
    private TextView textViewDayMinTemp;

    public DailyWeatherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DailyWeatherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public DailyWeatherView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.custom_view_daily_weather, this);
        textViewDayOfTheWeek = (TextView) findViewById(R.id.textview_day_of_the_week);
        imageViewDayWeather = (ImageView) findViewById(R.id.imageview_day_weather);
        textViewDayMaxTemp = (TextView) findViewById(R.id.textview_day_max_temp);
        textViewDayMinTemp = (TextView) findViewById(R.id.textview_day_min_temp);
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        textViewDayOfTheWeek.setText(dayOfTheWeek);
    }

    public void setDayWeatherImage(int weatherImageResource) {
        imageViewDayWeather.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                weatherImageResource,
                null));
    }

    public void setMaxDayTemp(String maxDayTemp) {
        textViewDayMaxTemp.setText(maxDayTemp);
    }

    public void setMinDayTemp(String minDayTemp) {
        textViewDayMinTemp.setText(minDayTemp);
    }
}
