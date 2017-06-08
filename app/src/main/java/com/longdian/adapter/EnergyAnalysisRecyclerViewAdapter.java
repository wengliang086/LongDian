package com.longdian.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longdian.R;
import com.longdian.fragment.EnergyAnalysisFragment.OnListFragmentInteractionListener;

import java.util.Arrays;
import java.util.List;

public class EnergyAnalysisRecyclerViewAdapter extends RecyclerView.Adapter<EnergyAnalysisRecyclerViewAdapter.ViewHolder> {

    private final List<String> mValues;
    private final OnListFragmentInteractionListener mListener;
    private int mColumnCount = 1;

    public EnergyAnalysisRecyclerViewAdapter(List<String> items, OnListFragmentInteractionListener listener, int mColumnCount) {
        if (items == null) {
            items = Arrays.asList("信息总览", "实时数据", "历史数据", "日报表", "月报表", "年报表");
        }
        mValues = items;
        mListener = listener;
        this.mColumnCount = mColumnCount;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mColumnCount > 1 ? R.layout.fragment_energyanalysis2 : R.layout.fragment_energyanalysis, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIdView.setText(mValues.get(position));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mIdView.getText().toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
        }
    }
}
