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

        List<Map<String, String>> lists = ElectricAnalysisFragment.list;

        List<Integer> vs = Arrays.asList(80, 80, 80, 80, 80, 80, 80, 80);
        TestPanelAdapter testPanelAdapter = new TestPanelAdapter(createData(lists), TestPanelAdapter.width_type_dp, vs);
        scrollablePanel.setPanelAdapter(testPanelAdapter);
        return baseView;
    }

    private List<List<String>> createData(List<Map<String, String>> list) {
        List<List<String>> datas = new ArrayList<>();
        datas.add(Arrays.asList("换热站名称", "实际面积(m²)", "日期", "用电量", "运行单耗(T/m²)", "统计时间(小时)", "开始时间", "结束时间"));
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> s = list.get(i);
            List<String> row = Arrays.asList(s.get("station_name"), s.get("real_area"), s.get("dt"), s.get("jqi_total"), s.get("yxdhstr"), s.get("hours"), s.get("bDateTime"), s.get("eDateTime"));
            datas.add(row);
        }
        return datas;
    }
}
