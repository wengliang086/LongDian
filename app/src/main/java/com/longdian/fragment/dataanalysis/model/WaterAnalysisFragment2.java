package com.longdian.fragment.dataanalysis.model;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longdian.R;
import com.longdian.util.ToastUtils;

import java.util.List;

public class WaterAnalysisFragment2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_data_anaysis_water2, container, false);
        List<List<String>> lists = WaterAnalysisFragment.list;
        ToastUtils.showToast(getActivity(), "chart222");
        return baseView;
    }

}
