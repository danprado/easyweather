package com.daniloprado.weather.view.base;

import android.support.v4.app.DialogFragment;

import com.daniloprado.weather.MainApplication;
import com.daniloprado.weather.dagger.DiComponent;

public abstract class BaseDialogFragment extends DialogFragment {

    private MainApplication getMainApplication() {
        return (MainApplication) getActivity().getApplication();
    }

    protected DiComponent getDiComponent() {
        return getMainApplication().getDiComponent();
    }

}
