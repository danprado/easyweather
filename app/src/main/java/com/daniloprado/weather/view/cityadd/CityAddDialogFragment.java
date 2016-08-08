package com.daniloprado.weather.view.cityadd;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

import com.daniloprado.weather.R;
import com.daniloprado.weather.model.City;
import com.daniloprado.weather.util.ViewFlipperUtil;
import com.daniloprado.weather.view.base.BaseDialogFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class CityAddDialogFragment extends BaseDialogFragment implements CityAddContract.View {

    public static int REQUEST_CODE = 1;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.main_layout)
    LinearLayout mainLayout;

    @BindView(R.id.edittext_toolbar_city_search)
    EditText editTextCitySearch;

    @BindView(R.id.recyclerview_cities_found)
    RecyclerView recyclerView;

    @BindView(R.id.error_layout)
    LinearLayout errorLayout;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @BindView(R.id.viewflipper)
    ViewFlipper viewFlipper;

    @Inject
    CityAddPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDiComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_city_add, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setView(this);
        initToolbar();
        setupUi();
    }

    private void initToolbar() {
        setHasOptionsMenu(true);
        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeButtonEnabled(true);
            }
        }
    }

    private void setupUi() {
        editTextCitySearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.searchCities(editable.toString());
            }
        });

        editTextCitySearch.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editTextCitySearch, InputMethodManager.SHOW_IMPLICIT);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            close(Activity.RESULT_CANCELED);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void setupRecyclerViewAdapter(List<City> cityList) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        CitySearchAdapter adapter = new CitySearchAdapter(
                cityList,
                city -> presenter.onCitySelected(city));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showErrorCityAlreadyExists() {
        Snackbar.make(mainLayout, R.string.error_message_city_has_already_been_added, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void close(int result) {
        getFragmentManager().popBackStack();
        getTargetFragment().onActivityResult(REQUEST_CODE, result, null);
    }

    @Override
    public void showLoadingLayout() {
        ViewFlipperUtil.setDisplayedChild(viewFlipper, progressBar);
    }

    @Override
    public void showErrorLayout() {
        ViewFlipperUtil.setDisplayedChild(viewFlipper, errorLayout);
    }

    @Override
    public void showContentLayout() {
        ViewFlipperUtil.setDisplayedChild(viewFlipper, recyclerView);
    }

}
