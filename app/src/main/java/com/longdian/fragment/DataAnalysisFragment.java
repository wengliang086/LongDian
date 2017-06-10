package com.longdian.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longdian.R;
import com.longdian.activity.ContentActivity;
import com.longdian.fragment.runningstate.TableFragment;

public class DataAnalysisFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_anaysis, container, false);

        view.findViewById(R.id.id_water_consum).setOnClickListener(this);
        view.findViewById(R.id.id_heat_consum).setOnClickListener(this);
        view.findViewById(R.id.id_electric_consum).setOnClickListener(this);
        view.findViewById(R.id.id_economics_analysis).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_water_consum:
                ContentActivity.start(getActivity(), TableFragment.class);
                break;
            case R.id.id_heat_consum:
                ContentActivity.start(getActivity(), TableFragment.class);
                break;
            case R.id.id_electric_consum:
                ContentActivity.start(getActivity(), TableFragment.class);
                break;
            case R.id.id_economics_analysis:
                ContentActivity.start(getActivity(), TableFragment.class);
                break;
        }
    }
}
