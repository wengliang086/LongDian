package com.longdian.fragment.dataanalysis.model;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.longdian.R;
import com.longdian.fragment.runningstate.TestPanelAdapter;
import com.longdian.util.ToastUtils;

import java.util.List;

public class WaterAnalysisFragment1 extends Fragment {

    private ScrollablePanel scrollablePanel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_data_anaysis_water1, container, false);
        scrollablePanel = (ScrollablePanel) baseView.findViewById(R.id.id_scrollable_panel);

        ToastUtils.showToast(getActivity(), "111");
        List<List<String>> lists = WaterAnalysisFragment.list;
        TestPanelAdapter testPanelAdapter = new TestPanelAdapter(lists);
        scrollablePanel.setPanelAdapter(testPanelAdapter);
        return baseView;
    }
}
