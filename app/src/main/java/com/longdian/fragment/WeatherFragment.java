package com.longdian.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longdian.R;
import com.longdian.activity.ContentActivity;
import com.longdian.activity.ContentActivityNoToolbar;
import com.longdian.fragment.weather.TableWeatherFragment;
import com.longdian.fragment.weather.WeatherDetailFragment;
import com.longdian.fragment.weather.WeatherOverviewFragment;

public class WeatherFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        view.findViewById(R.id.id_weather_now).setOnClickListener(this);
        view.findViewById(R.id.id_weather_detail).setOnClickListener(this);
        view.findViewById(R.id.id_weather_table).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_weather_now:// 天气概览
                ContentActivityNoToolbar.start(getActivity(), WeatherOverviewFragment.class);
                break;
            case R.id.id_weather_detail:
                ContentActivity.start(getActivity(), WeatherDetailFragment.class, "天气详情");
                break;
            case R.id.id_weather_table:
                ContentActivity.start(getActivity(), TableWeatherFragment.class, "天气情况查询");
                break;
        }
    }
}
