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
        holder.stationName.setText(s.getStationName());
        holder.persion.setText("负责人\n" + s.getPersonnel());
        holder.phone.setText("负责人电话\n" + s.getTelephone());

        if (openList.get(position)) {
            onShow(holder, s);
        } else {
            onHide(holder);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((LinearLayout) holder.itemView).getChildCount() == 1) {
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

        LinearLayout ll = (LinearLayout) holder.itemView;
        for (int i = ll.getChildCount() - 1; i >= 1; i--) {
            ll.removeViewAt(i);
        }
    }

    private void onShow(ViewHolder holder, StationData s) {
        holder.imageView.setImageResource(R.drawable.ic_triangle_down);
        LinearLayout ll = (LinearLayout) holder.itemView;
        LogUtil.e("添加");
        List<StandData> list = stationList.getById(s.getStationId());
        for (StandData data : list) {
            View vv = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.fragment_station_2, ll, false);
            ((TextView) vv.findViewById(R.id.id_stand_name)).setText(data.getStandName());
            ((TextView) vv.findViewById(R.id.id_stand_d_area)).setText("设计面积\n" + data.getDesignArea() + "㎡");
            ((TextView) vv.findViewById(R.id.id_stand_r_area)).setText("实际面积\n" + data.getRealArea() + "㎡");
            ll.addView(vv);
        }
    }

    @Override
    public int getItemCount() {
        return stationList.getStationDataList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView stationName;
        TextView persion;
        TextView phone;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            stationName = (TextView) view.findViewById(R.id.id_station_name);
            persion = (TextView) view.findViewById(R.id.id_persion);
            phone = (TextView) view.findViewById(R.id.id_phone);
            imageView = (ImageView) view.findViewById(R.id.id_station_arraw);
        }
    }
}
