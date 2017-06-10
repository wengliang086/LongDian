package com.longdian.fragment.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.longdian.R;
import com.longdian.fragment.runningstate.TestPanelAdapter;
import com.longdian.fragment.weather.model.WeatherData;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeatherOverviewFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View baseView = inflater.inflate(R.layout.fragment_weather_overview, container, false);

        HoolaiHttpMethods.getInstance().weatherList(getActivity(), new ObserverOnNextAndErrorListener<List<WeatherData>>() {
            @Override
            public void onNext(List<WeatherData> weatherDataList) {
                List<Integer> vs = Arrays.asList(40, 80, 80, 80, 80, 80, 80, 80, 80);
                TestPanelAdapter testPanelAdapter = new TestPanelAdapter(createData(weatherDataList), TestPanelAdapter.width_type_dp, vs);
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
