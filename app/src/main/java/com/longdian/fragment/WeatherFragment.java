package com.longdian.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longdian.R;
import com.longdian.activity.ContentActivity;
import com.longdian.fragment.runningstate.TableFragment;

public class WeatherFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        view.findViewById(R.id.id_weather_now).setOnClickListener(this);
        view.findViewById(R.id.id_weather_table).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_weather_now:
                ContentActivity.start(getActivity(), TableFragment.class);
                break;
            case R.id.id_weather_table:
                ContentActivity.start(getActivity(), TableFragment.class);
                break;
        }
    }
}
