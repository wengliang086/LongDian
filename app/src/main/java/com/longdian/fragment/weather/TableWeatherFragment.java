package com.longdian.fragment.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.longdian.R;
import com.longdian.fragment.BaseDatePickerFragment;
import com.longdian.fragment.runningstate.TestPanelAdapter;
import com.longdian.fragment.weather.model.WeatherData;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableWeatherFragment extends BaseDatePickerFragment {

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
        HoolaiHttpMethods.getInstance().weatherList(getActivity(), new ObserverOnNextAndErrorListener<List<WeatherData>>() {
            @Override
            public void onNext(List<WeatherData> weatherDataList) {
                List<Integer> vs = Arrays.asList(40, 80, 80, 80, 80, 80, 80, 80, 80);
                TestPanelAdapter testPanelAdapter = new TestPanelAdapter(createData(weatherDataList), TestPanelAdapter.width_type_dp, vs);
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

    private List<List<String>> createData(List<WeatherData> list) {
        List<List<String>> datas = new ArrayList<>();
        datas.add(Arrays.asList("序号", "预报日期", "白天最高温", "夜晚最底温", "平均温度", "白天天气状况", "夜晚天气状况", "白天风力风向", "夜晚风力风向"));
        for (int i = 0; i < list.size(); i++) {
            WeatherData s = list.get(i);
            List<String> row = Arrays.asList(i + 1 + "", s.getDay(), s.getTeH() + "", s.getTeL() + "", s.getTeA() + "", s.getTeC1(), s.getTeC2(), s.getTeWd1() + s.getTeWe1(), s.getTeWd2() + s.getTeWe2());
            datas.add(row);
        }
        return datas;
    }
}
