package com.longdian.fragment.runningstate;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.longdian.R;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class ReportYearFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {

    private ScrollablePanel scrollablePanel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View baseView = inflater.inflate(R.layout.fragment_report_year, container, false);
        init(baseView);
        getData("2017");
        return baseView;
    }

    private void getData(String date) {
        HoolaiHttpMethods.getInstance().reportYear(getActivity(), date, textViewName.getText().toString(), new ObserverOnNextAndErrorListener<List<Map<String, String>>>() {
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

    // ------ **** ----------
    protected TextView textViewStart;
    protected TextView textViewName;

    protected void initDatePicker(View baseView) {
        textViewStart = (TextView) baseView.findViewById(R.id.id_date_start);
        textViewStart.setOnTouchListener(this);
        textViewName = (TextView) baseView.findViewById(R.id.id_station_name);
        baseView.findViewById(R.id.id_search).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_search:
                getData(textViewStart.getText().toString());
                break;
        }
    }

    private void showDatePickerDialog(final TextView textView) {
        String dateStr = textView.getText().toString();
        String[] array = dateStr.split("-");

        Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        if (array.length == 3) {
            year = Integer.parseInt(array[0]);
            month = Integer.parseInt(array[1]) - 1;
            dayOfMonth = Integer.parseInt(array[2]);
        }
        DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                textView.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        }, year, month, dayOfMonth);
        dialog.show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            switch (v.getId()) {
                case R.id.id_date_start:
                    showDatePickerDialog(textViewStart);
                    break;
            }
        }
        return false;
    }
}
