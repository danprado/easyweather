package com.daniloprado.weather.view.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.ButterKnife;

public abstract class ContractFragment<T> extends BaseFragment {

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    protected final T getContract() {
        return mContract;
    }

}
