package com.longdian.fragment.runningstate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.longdian.R;
import com.longdian.fragment.dataanalysis.model.CollectExtendData;

import java.util.List;

public class RealtimeDataRecyclerViewAdapter extends RecyclerView.Adapter<RealtimeDataRecyclerViewAdapter.ViewHolder> {

    private List<CollectExtendData> dataList;

    public RealtimeDataRecyclerViewAdapter(List<CollectExtendData> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_realtime, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CollectExtendData s = dataList.get(position);
        holder.mIdView.setText(s.getStationName());
        holder.mContentView.setText(s.getLt1() + "");
        holder.mContentView2.setText(s.getQqi() + "");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout ll = holder.linearLayout;
                int a = ll.getVisibility();
                if (a == View.GONE) {
                    holder.imageView.setImageResource(R.drawable.ic_wind_5);
                    ll.setVisibility(View.VISIBLE);

                    if (ll.getChildCount() <= 1) {// 防止重复添加
                        // 一网数据
                        View vv1 = LayoutInflater.from(v.getContext()).inflate(R.layout.fragment_realtime_2, ll, false);
                        ((TextView) vv1.findViewById(R.id.id_stand_name)).setText(s.getPt1() + " " + s.getPt2());
                        ((TextView) vv1.findViewById(R.id.id_stand_d_area)).setText(s.getTe1() + " " + s.getTe2());
                        ((TextView) vv1.findViewById(R.id.id_stand_r_area)).setText(s.getCvi1() + "");
                        ll.addView(vv1);
                        // 二网数据
                        View vv2 = LayoutInflater.from(v.getContext()).inflate(R.layout.fragment_realtime_2, ll, false);
                        ((TextView) vv2.findViewById(R.id.id_stand_name)).setText(s.getPt3() + " " + s.getPt4());
                        ((TextView) vv2.findViewById(R.id.id_stand_d_area)).setText(s.getTe3() + " " + s.getTe4());
                        ((TextView) vv2.findViewById(R.id.id_stand_r_area)).setText(s.getFt3q() + "");
                        ll.addView(vv2);
                    }
                } else {
                    holder.imageView.setImageResource(R.drawable.ic_wind_3);
                    ll.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mContentView2;
        ImageView imageView;
        LinearLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mContentView2 = (TextView) view.findViewById(R.id.content2);
            imageView = (ImageView) view.findViewById(R.id.id_station_arraw);
            linearLayout = (LinearLayout) view.findViewById(R.id.id_station_linearLayout);
            linearLayout.setVisibility(View.GONE);
        }
    }
}
