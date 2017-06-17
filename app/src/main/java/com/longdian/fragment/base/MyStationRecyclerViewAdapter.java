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

import java.util.List;

public class MyStationRecyclerViewAdapter extends RecyclerView.Adapter<MyStationRecyclerViewAdapter.ViewHolder> {

    private StationList stationList;

    public MyStationRecyclerViewAdapter(StationList stationList) {
        this.stationList = stationList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_station, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final StationData s = stationList.getStationDataList().get(position);
        holder.mIdView.setText(s.getStationName());
        holder.mContentView.setText(s.getPersonnel());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout ll = holder.linearLayout;
                int a = ll.getVisibility();
                if (a == View.GONE) {
                    holder.imageView.setImageResource(R.drawable.ic_wind_5);
                    ll.setVisibility(View.VISIBLE);

                    if (ll.getChildCount() <= 1) {// 防止重复添加
                        List<StandData> list = stationList.getById(s.getStationId());
                        for (StandData data : list) {
                            View vv = LayoutInflater.from(v.getContext()).inflate(R.layout.fragment_station_2, ll, false);
                            ((TextView) vv.findViewById(R.id.id_stand_name)).setText(data.getStandName());
                            ((TextView) vv.findViewById(R.id.id_stand_d_area)).setText(data.getDesignArea() + "");
                            ((TextView) vv.findViewById(R.id.id_stand_r_area)).setText(data.getRealArea() + "");
                            ll.addView(vv);
                        }
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
        return stationList.getStationDataList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        ImageView imageView;
        LinearLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            imageView = (ImageView) view.findViewById(R.id.id_station_arraw);
            linearLayout = (LinearLayout) view.findViewById(R.id.id_station_linearLayout);
            linearLayout.setVisibility(View.GONE);
        }
    }
}
