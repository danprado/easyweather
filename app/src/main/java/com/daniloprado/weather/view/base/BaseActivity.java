package com.daniloprado.weather.view.base;


import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import com.daniloprado.weather.MainApplication;
import com.daniloprado.weather.dagger.DiComponent;

public class BaseActivity extends AppCompatActivity {

    protected MainApplication getMainApplication() {
        Application application = getApplication();
        if (application instanceof MainApplication) {
            return (MainApplication) application;
        }
        return null;
    }

    protected DiComponent getDiComponent() {
        return getMainApplication().getDiComponent();
    }

}
