package com.longdian.fragment.dataanalysis;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.longdian.R;
import com.longdian.fragment.BaseDatePickerFragment;
import com.longdian.fragment.runningstate.TestPanelAdapter;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EconomicsAnalysisFragment extends BaseDatePickerFragment {

    private ScrollablePanel scrollablePanel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View baseView = inflater.inflate(R.layout.activity_scrollable_panel_test, container, false);
        init(baseView);
        getData("2017-06-17", "2017-06-17", "");
        return baseView;
    }

    private void getData(String start, String end, String name) {
        HoolaiHttpMethods.getInstance().reportEconomics(getActivity(), start, end, name, new ObserverOnNextAndErrorListener<List<Map<String, String>>>() {
            @Override
            public void onNext(List<Map<String, String>> dataList) {
                List<Integer> vs = Arrays.asList(80, 80, 80, 80, 80, 80, 80, 80, 80, 80);
                TestPanelAdapter testPanelAdapter = new TestPanelAdapter(createData(dataList), TestPanelAdapter.width_type_dp, vs);
                scrollablePanel.setPanelAdapter(testPanelAdapter);
            }

            @Override
            public void onError(HoolaiException e) {
                ToastUtils.showToast(getActivity(), e.getMessage());
            }
        });
    }

    private void init(View view) {
        scrollablePanel = (ScrollablePanel) view.findViewById(R.id.id_scrollable_panel);
        initDatePicker(view);
    }

    @Override
    protected void doSearch() {
        String start = textViewStart.getText().toString();
        String end = textViewEnd.getText().toString();
        getData(start, end, "");
    }

    private List<List<String>> createData(List<Map<String, String>> list) {
        List<List<String>> datas = new ArrayList<>();
        datas.add(Arrays.asList("换热站名称", "建筑面积", "设计面积", "累计水量(T)", "水费(元)", "累计电量(KWh)", "电费(元)", "累计热量(GJ)", "热费(元)", "总额(元)"));
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> m = list.get(i);
            List<String> row = Arrays.asList(m.get("station_name"), m.get("sum_real_area"), m.get("sum_design_area"), m.get("sum_ft3q"), m.get("sum_ft3q_price"),
                    m.get("sum_jqi"), m.get("sum_jqi_price"), m.get("sum_qqi"), m.get("sum_qqi_price"), m.get("price_total"));
            datas.add(row);
        }
        return datas;
    }
}
