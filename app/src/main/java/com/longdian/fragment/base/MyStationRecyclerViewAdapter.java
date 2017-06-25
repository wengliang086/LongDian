package com.longdian.fragment.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.longdian.R;
import com.longdian.fragment.base.model.StandData;
import com.longdian.fragment.base.model.StationData;
import com.longdian.fragment.base.model.StationList;
import com.longdian.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class MyStationRecyclerViewAdapter extends RecyclerView.Adapter<MyStationRecyclerViewAdapter.ViewHolder> {

    private StationList stationList;
    private List<Boolean> openList = new ArrayList<>();// 存储打开状态

    public MyStationRecyclerViewAdapter(StationList stationList) {
        this.stationList = stationList;
        for (int i = 0; i < stationList.getStationDataList().size(); i++) {
            openList.add(false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_station, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final StationData s = stationList.getStationDataList().get(position);
        holder.mIdView.setText(s.getStationName());
        holder.mContentView.setText(s.getPersonnel());

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
        holder.imageView.setImageResource(R.drawable.ic_more_pressed);
        holder.linearLayout.setVisibility(View.GONE);
    }

    private void onShow(ViewHolder holder, StationData s) {
        LinearLayout ll = holder.linearLayout;
        holder.imageView.setImageResource(R.drawable.ic_triangle_down);
        ll.setVisibility(View.VISIBLE);
        ll.removeAllViews();
        LogUtil.e("添加");
        List<StandData> list = stationList.getById(s.getStationId());
        for (StandData data : list) {
            View vv = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.fragment_station_2, ll, false);
            ((TextView) vv.findViewById(R.id.id_stand_name)).setText(data.getStandName());
            ((TextView) vv.findViewById(R.id.id_stand_d_area)).setText(data.getDesignArea() + "");
            ((TextView) vv.findViewById(R.id.id_stand_r_area)).setText(data.getRealArea() + "");
            ll.addView(vv);
        }
    }

    @Override
    public int getItemCount() {
        return stationList.getStationDataList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mIdView;
        TextView mContentView;
        ImageView imageView;
        LinearLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            imageView = (ImageView) view.findViewById(R.id.id_station_arraw);
            linearLayout = (LinearLayout) view.findViewById(R.id.id_station_linearLayout);
            linearLayout.setVisibility(View.GONE);
        }
    }
}
