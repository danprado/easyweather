package com.daniloprado.weather.view.base;


import android.support.v7.app.AppCompatActivity;

import com.daniloprado.weather.MainApplication;
import com.daniloprado.weather.dagger.DiComponent;

public abstract class BaseActivity extends AppCompatActivity {

    private MainApplication getMainApplication() {
        return (MainApplication) getApplication();
    }

    protected DiComponent getDiComponent() {
        return getMainApplication().getDiComponent();
    }

}
