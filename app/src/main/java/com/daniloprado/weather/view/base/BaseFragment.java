package com.daniloprado.weather.view.base;

import android.support.v4.app.Fragment;

import com.daniloprado.weather.MainApplication;
import com.daniloprado.weather.dagger.DiComponent;

public class BaseFragment extends Fragment {

    private MainApplication getMainApplication() {
        return (MainApplication) getActivity().getApplication();
    }

    protected DiComponent getDiComponent() {
        return getMainApplication().getDiComponent();
    }

}
