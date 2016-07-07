package com.daniloprado.weather.view.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.daniloprado.weather.MainApplication;
import com.daniloprado.weather.dagger.DiComponent;

public abstract class ContractFragment<T> extends Fragment {

    private T mContract;

    @Override
    public void onAttach(Context context) {
        try {
            mContract = (T) context;
        } catch (ClassCastException e) {
            throw new IllegalStateException(context.getClass().getSimpleName()
                    + " does not implement " + getClass().getSimpleName() + "'s contract interface.", e);
        }
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContract = null;
    }

    protected final T getContract() {
        return mContract;
    }

    private MainApplication getMainApplication() {
        return (MainApplication) getActivity().getApplication();
    }

    protected DiComponent getDiComponent() {
        return getMainApplication().getDiComponent();
    }

}
