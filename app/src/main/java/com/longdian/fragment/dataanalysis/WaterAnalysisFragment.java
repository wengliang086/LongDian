package com.longdian.fragment.dataanalysis;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longdian.R;
import com.longdian.activity.TopBarBaseActivity;
import com.longdian.fragment.BaseDatePickerFragment;

import java.util.ArrayList;
import java.util.List;

public class WaterAnalysisFragment extends BaseDatePickerFragment {

    private FragmentManager fragmentManager;
    private boolean isChart = false;
    public static List<List<String>> list;

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

        int rows = 15;
        if (!TextUtils.isEmpty(start)) {
            rows = Integer.parseInt(start.substring(start.length() - 1));
        }
        list = createData(rows);
        if (!isChart) {
            replaceFragment(new WaterAnalysisFragment1());
        } else {
            replaceFragment(new WaterAnalysisFragment2());
        }
    }

    private List<List<String>> createData(int rows) {
        List<List<String>> datas = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                row.add(i + "-" + j);
            }
            datas.add(row);
        }
        return datas;
    }
}
