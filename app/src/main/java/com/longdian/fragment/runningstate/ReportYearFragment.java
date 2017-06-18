package com.longdian.fragment.runningstate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.longdian.R;
import com.longdian.fragment.BaseDatePickerFragment;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReportYearFragment extends BaseDatePickerFragment {

    private ScrollablePanel scrollablePanel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View baseView = inflater.inflate(R.layout.activity_scrollable_panel_test, container, false);
        init(baseView);
        getData();
        return baseView;
    }

    private void getData() {
        HoolaiHttpMethods.getInstance().reportYear(getActivity(), "2017", "", new ObserverOnNextAndErrorListener<List<Map<String, String>>>() {
            @Override
            public void onNext(List<Map<String, String>> dataList) {
                List<Integer> vs = Arrays.asList(80, 80, 80, 80, 80, 80, 80);
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
        getData();
    }

    private List<List<String>> createData(List<Map<String, String>> list) {
        List<List<String>> datas = new ArrayList<>();
        datas.add(Arrays.asList("换热站名称", "供热量(GJ)", "累积量(GJ)", "供热量(KWh)", "累积量(KWh)", "供热量(T)", "累积量(T)"));
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> m = list.get(i);
            List<String> row = Arrays.asList(m.get("station_name"), m.get("day_ft3q"), m.get("day_ft3q_total"), m.get("day_qqi"), m.get("day_qqi_total"), m.get("day_jqi"), m.get("day_jqi_total"));
            datas.add(row);
        }
        return datas;
    }
}
