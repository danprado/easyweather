package com.daniloprado.weather.view.citylist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daniloprado.weather.R;
import com.daniloprado.weather.model.City;
import com.daniloprado.weather.view.base.ContractFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityListFragment extends ContractFragment<CityListFragment.Contract> implements CityListContract.View {

    public interface Contract {
        void onCitySelected(City city);
    }

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
