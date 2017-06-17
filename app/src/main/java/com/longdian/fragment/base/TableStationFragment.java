package com.longdian.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.longdian.R;
import com.longdian.fragment.BaseDatePickerFragment;
import com.longdian.fragment.base.model.StationData;
import com.longdian.fragment.base.model.StationList;
import com.longdian.fragment.runningstate.TestPanelAdapter;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableStationFragment extends BaseDatePickerFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View baseView = inflater.inflate(R.layout.activity_scrollable_panel_test, container, false);

        initDatePicker(baseView);

        HoolaiHttpMethods.getInstance().stationList(getActivity(), new ObserverOnNextAndErrorListener<StationList>() {
            @Override
            public void onNext(StationList stationList) {
                List<Integer> vs = Arrays.asList(10, 7, 13, 8, 8);
                TestPanelAdapter testPanelAdapter = new TestPanelAdapter(createData(stationList), TestPanelAdapter.width_type_weight, vs);
                ScrollablePanel scrollablePanel = (ScrollablePanel) baseView.findViewById(R.id.id_scrollable_panel);
                scrollablePanel.setPanelAdapter(testPanelAdapter);
            }

            @Override
            public void onError(HoolaiException e) {
                ToastUtils.showToast(getActivity(), e.getMessage());
            }
        });
        return baseView;
    }

    private List<List<String>> createData(StationList stationList) {
        List<List<String>> datas = new ArrayList<>();
        List<StationData> list = stationList.getStationDataList();
        datas.add(Arrays.asList("换热站名称", "负责人", "负责人电话", "设计面积", "实际面积"));
        for (int i = 0; i < list.size(); i++) {
            StationData s = list.get(i);
            List<String> row = Arrays.asList(s.getStationName(), s.getPersonnel(), s.getTelephone(), "10", "M2");
            datas.add(row);
        }
        return datas;
    }

    @Override
    protected void doSearch() {

    }
}
