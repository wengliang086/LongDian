package com.longdian.fragment.runningstate;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longdian.R;
import com.longdian.fragment.BaseDatePickerFragment;
import com.longdian.fragment.dataanalysis.model.CollectExtendData;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.ToastUtils;

import java.util.List;

public class HistoryDataFragment extends BaseDatePickerFragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View baseView = inflater.inflate(R.layout.fragment_history_data, container, false);
        init(baseView);
        getData("2017-06-17");
        return baseView;
    }

    private void getData(String date) {
        HoolaiHttpMethods.getInstance().historyData(getActivity(), date, new ObserverOnNextAndErrorListener<List<CollectExtendData>>() {
            @Override
            public void onNext(List<CollectExtendData> dataList) {
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                recyclerView.setAdapter(new RealtimeDataRecyclerViewAdapter(dataList));
            }

            @Override
            public void onError(HoolaiException e) {
                ToastUtils.showToast(getActivity(), e.getMessage());
            }
        });
    }

    private void init(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        initDatePicker(view);
    }

    @Override
    protected void doSearch() {
        getData(textViewStart.getText().toString());
    }

}
