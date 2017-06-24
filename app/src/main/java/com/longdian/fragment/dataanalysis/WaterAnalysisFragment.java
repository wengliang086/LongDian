package com.longdian.fragment.dataanalysis;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longdian.R;
import com.longdian.activity.TopBarBaseActivity;
import com.longdian.fragment.BaseDatePickerFragment;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WaterAnalysisFragment extends BaseDatePickerFragment {

    private FragmentManager fragmentManager;
    private boolean isChart = false;
    public static List<Map<String, String>> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_data_anaysis_water, container, false);

        initDatePicker(baseView);

        final TopBarBaseActivity activity = (TopBarBaseActivity) getActivity();
        activity.setTopRightButton("bbb", R.drawable.ic_action_accept, new TopBarBaseActivity.OnClickListener() {
            @Override
            public void onClick() {
                if (isChart) {
                    replaceFragment(new WaterAnalysisFragment1());
                } else {
                    replaceFragment(new WaterAnalysisFragment2());
                }
                isChart = !isChart;
            }
        });
        fragmentManager = activity.getSupportFragmentManager();

        textViewStart.setText("2017-06-17");
        textViewEnd.setText("2017-06-17");
        doSearch();
        return baseView;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_fragment, fragment, fragment.getClass().getName());
        transaction.commit();
    }

    @Override
    protected void doSearch() {
        String start = textViewStart.getText().toString();
        String end = textViewEnd.getText().toString();

        HoolaiHttpMethods.getInstance().analysisWater(getActivity(), start, end, "", new ObserverOnNextAndErrorListener<List<Map<String, String>>>() {
            @Override
            public void onNext(List<Map<String, String>> maps) {
                list = maps;
                if (!isChart) {
                    replaceFragment(new WaterAnalysisFragment1());
                } else {
                    replaceFragment(new WaterAnalysisFragment2());
                }
            }

            @Override
            public void onError(HoolaiException e) {

            }
        });
    }

}
