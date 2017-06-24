package com.longdian.fragment.weather.model;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WeatherXAxisValueFormatter implements IAxisValueFormatter {

    private List<String> names = new ArrayList<>();

    public WeatherXAxisValueFormatter(List<Map<String, Object>> mapList) {
        for (Map<String, Object> m : mapList) {
            names.add((String) m.get("d"));
        }
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        int index = (int) value;
        return names.get(index).split("日")[1];
    }
}
