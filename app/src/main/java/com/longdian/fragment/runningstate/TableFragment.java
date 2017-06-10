package com.longdian.fragment.runningstate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.longdian.R;

import java.util.ArrayList;
import java.util.List;

public class TableFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.activity_scrollable_panel_test, container, false);
        TestPanelAdapter testPanelAdapter = new TestPanelAdapter(createData());
        ScrollablePanel scrollablePanel = (ScrollablePanel) baseView.findViewById(R.id.id_scrollable_panel);
        scrollablePanel.setPanelAdapter(testPanelAdapter);
        return baseView;
    }

    private List<List<String>> createData() {
        List<List<String>> datas = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                row.add(i + "-" + j);
            }
            datas.add(row);
        }
        return datas;
    }
}
