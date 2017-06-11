package com.longdian.fragment.weather;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

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

public class TableWeatherFragment extends Fragment implements View.OnClickListener {

    private ScrollablePanel scrollablePanel;
    private TextView textViewStart;
    private TextView textViewEnd;

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
        textViewStart = (TextView) view.findViewById(R.id.id_date_start);
        textViewStart.setOnClickListener(this);
        textViewEnd = (TextView) view.findViewById(R.id.id_date_end);
        textViewEnd.setOnClickListener(this);
        view.findViewById(R.id.id_search).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_date_start:
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textViewStart.setText(year + "-" + month + "-" + dayOfMonth);
                    }
                }, 2017, 6, 10);
                dialog.show();
                break;
            case R.id.id_date_end:
                DatePickerDialog dialog1 = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textViewEnd.setText(year + "-" + month + "-" + dayOfMonth);
                    }
                }, 2017, 6, 10);
                dialog1.show();
                break;
            case R.id.id_search:
                getData();
                break;
        }
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
