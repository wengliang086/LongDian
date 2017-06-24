package com.longdian.fragment.dataanalysis;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ElectricAnalysisFragment1 extends Fragment {

    private ScrollablePanel scrollablePanel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_data_anaysis_water1, container, false);
        scrollablePanel = (ScrollablePanel) baseView.findViewById(R.id.id_scrollable_panel);

        ToastUtils.showToast(getActivity(), "111");
        List<Map<String, String>> lists = WaterAnalysisFragment.list;
        TestPanelAdapter testPanelAdapter = new TestPanelAdapter(createData(lists));
        scrollablePanel.setPanelAdapter(testPanelAdapter);
        return baseView;
    }

    private List<List<String>> createData(List<Map<String, String>> list) {
        List<List<String>> datas = new ArrayList<>();
        datas.add(Arrays.asList("序号", "预报日期", "白天最高温", "夜晚最底温", "平均温度", "白天天气状况", "夜晚天气状况", "白天风力风向", "夜晚风力风向"));
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> s = list.get(i);
            List<String> row = Arrays.asList(i + 1 + "", s.get(""), s.get(""), s.get(""), s.get(""), s.get(""), s.get(""), s.get(""), s.get(""));
            datas.add(row);
        }
        return datas;
    }
}
