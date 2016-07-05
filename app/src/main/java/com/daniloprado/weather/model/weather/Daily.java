package com.daniloprado.weather.model.weather;

import com.daniloprado.weather.model.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class Daily extends BaseModel {

    public String summary;
    public String icon;
    public List<Data> data = new ArrayList<>();

}
