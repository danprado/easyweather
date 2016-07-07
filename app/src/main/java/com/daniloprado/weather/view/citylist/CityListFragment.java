package com.daniloprado.weather.view.citylist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daniloprado.weather.R;
import com.daniloprado.weather.model.City;
import com.daniloprado.weather.view.base.ContractFragment;
import com.daniloprado.weather.view.cityadd.CityAddDialogFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityListFragment extends ContractFragment<CityListFragment.Contract> implements CityListContract.View {

    public interface Contract {
        void onCitySelected(City city);
        void onCityAddDialogDismissed();
    }

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Inject CityListContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDiComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter.setView(this);
        setupUi();
    }

    @Override
    public void setupRecyclerViewAdapter(List<City> cityList) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        CityListAdapter adapter = new CityListAdapter(
                cityList,
                city -> getContract().onCitySelected(city));
        recyclerView.setAdapter(adapter);
    }

    private void setupUi() {
        fab.setOnClickListener(view -> {
            FragmentManager fragmentManager = getFragmentManager();
            CityAddDialogFragment cityAddFragment = new CityAddDialogFragment();
            cityAddFragment.setTargetFragment(this, CityAddDialogFragment.REQUEST_CODE);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.add(android.R.id.content, cityAddFragment).addToBackStack(null).commit();
        });

//        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//
//            }
//        };
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CityAddDialogFragment.REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                presenter.loadData();
            }

            getContract().onCityAddDialogDismissed();
        }
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
    public void showEmptyLayout() {

    }

}
