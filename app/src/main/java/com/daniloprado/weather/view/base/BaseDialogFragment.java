package com.daniloprado.weather.view.base;

import android.app.Application;
import android.support.v4.app.DialogFragment;

import com.daniloprado.weather.MainApplication;
import com.daniloprado.weather.dagger.DiComponent;

public abstract class BaseDialogFragment extends DialogFragment {

    protected MainApplication getMainApplication() {
        Application application = getActivity().getApplication();
        if (application instanceof MainApplication) {
            return (MainApplication) application;
        }
        return null;
    }

    protected DiComponent getDiComponent() {
        return getMainApplication().getDiComponent();
    }

}
