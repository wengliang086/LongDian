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
import com.longdian.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class RealtimeDataRecyclerViewAdapter extends RecyclerView.Adapter<RealtimeDataRecyclerViewAdapter.ViewHolder> {

    private List<CollectExtendData> dataList;
    private List<Boolean> openList = new ArrayList<>();// 存储打开状态

    public RealtimeDataRecyclerViewAdapter(List<CollectExtendData> dataList) {
        this.dataList = dataList;
        for (int i = 0; i < dataList.size(); i++) {
            openList.add(false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_realtime, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        LogUtil.e("position=" + position + holder.toString());
        final CollectExtendData s = dataList.get(position);
        holder.mIdView.setText(s.getStationName());
        holder.mContentView.setText(s.getLt1() + "");
        holder.mContentView2.setText(s.getQqi() + "");

        if (openList.get(position)) {
            onShow(holder, s);
        } else {
            onHide(holder);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.linearLayout.getVisibility() == View.GONE) {
                    openList.set(position, true);
                    onShow(holder, s);
                } else {
                    openList.set(position, false);
                    onHide(holder);
                }
            }
        });
    }

    private void onHide(ViewHolder holder) {
        holder.imageView.setImageResource(R.drawable.ic_wind_3);
        holder.linearLayout.setVisibility(View.GONE);
    }

    private void onShow(ViewHolder holder, CollectExtendData s) {
        LinearLayout ll = holder.linearLayout;
        holder.imageView.setImageResource(R.drawable.ic_wind_5);
        ll.setVisibility(View.VISIBLE);

        if (ll.getChildCount() <= 1) {// 防止重复添加
            LogUtil.e("添加");
            // 一网数据
            View vv1 = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.fragment_realtime_2, ll, false);
            ((TextView) vv1.findViewById(R.id.id_stand_name)).setText(s.getPt1() + " " + s.getPt2());
            ((TextView) vv1.findViewById(R.id.id_stand_d_area)).setText(s.getTe1() + " " + s.getTe2());
            ((TextView) vv1.findViewById(R.id.id_stand_r_area)).setText(s.getCvi1() + "");
            ll.addView(vv1);
            // 二网数据
            View vv2 = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.fragment_realtime_2, ll, false);
            ((TextView) vv2.findViewById(R.id.id_stand_name)).setText(s.getPt3() + " " + s.getPt4());
            ((TextView) vv2.findViewById(R.id.id_stand_d_area)).setText(s.getTe3() + " " + s.getTe4());
            ((TextView) vv2.findViewById(R.id.id_stand_r_area)).setText(s.getFt3q() + "");
            ll.addView(vv2);
        } else {
            LogUtil.e("更新");
            // 一网数据
            View vv1 = ll.getChildAt(1);
            ((TextView) vv1.findViewById(R.id.id_stand_name)).setText(s.getPt1() + " " + s.getPt2());
            ((TextView) vv1.findViewById(R.id.id_stand_d_area)).setText(s.getTe1() + " " + s.getTe2());
            ((TextView) vv1.findViewById(R.id.id_stand_r_area)).setText(s.getCvi1() + "");
            // 二网数据
            View vv2 = ll.getChildAt(2);
            ((TextView) vv2.findViewById(R.id.id_stand_name)).setText(s.getPt3() + " " + s.getPt4());
            ((TextView) vv2.findViewById(R.id.id_stand_d_area)).setText(s.getTe3() + " " + s.getTe4());
            ((TextView) vv2.findViewById(R.id.id_stand_r_area)).setText(s.getFt3q() + "");
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mIdView;
        TextView mContentView;
        TextView mContentView2;
        ImageView imageView;
        LinearLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mContentView2 = (TextView) view.findViewById(R.id.content2);
            imageView = (ImageView) view.findViewById(R.id.id_station_arraw);
            linearLayout = (LinearLayout) view.findViewById(R.id.id_station_linearLayout);
            linearLayout.setVisibility(View.GONE);
        }
    }
}
