package com.longdian.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.longdian.R;
import com.longdian.activity.ContentActivity;
import com.longdian.fragment.runningstate.HistoryDataFragment;
import com.longdian.fragment.runningstate.PieChartFragment;
import com.longdian.fragment.runningstate.RealtimeDataFragment;
import com.longdian.fragment.runningstate.ReportDayFragment;
import com.longdian.fragment.runningstate.ReportMonthFragment;
import com.longdian.fragment.runningstate.ReportYearFragment;

import java.util.Arrays;
import java.util.List;

public class EnergyAnalysisRecyclerViewAdapter extends RecyclerView.Adapter<EnergyAnalysisRecyclerViewAdapter.ViewHolder> {

    private List<Entity> mValues = Arrays.asList(
            new Entity("信息总览", PieChartFragment.class, R.drawable.t1_pie_chart_72px, "水电热信息概况"),
            new Entity("实时数据", RealtimeDataFragment.class, R.drawable.ic_more_give_good_reputation, "换热站一网、二网实时数据"),
            new Entity("历史数据", HistoryDataFragment.class, R.drawable.t1_history_72px, "按日查看换热站一网、二网数据"),
            new Entity("日报表", ReportDayFragment.class, R.drawable.ic_more_suggestion_feedback, "日数据统计"),
            new Entity("月报表", ReportMonthFragment.class, R.drawable.ic_more_suggestion_feedback, "月数据统计"),
            new Entity("年报表", ReportYearFragment.class, R.drawable.ic_more_suggestion_feedback, "年数据统计")
    );
    private int mColumnCount = 1;
    private Context context;

    public EnergyAnalysisRecyclerViewAdapter(Activity activity, int mColumnCount) {
        this.mColumnCount = mColumnCount;
        this.context = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mColumnCount > 1 ? R.layout.fragment_energyanalysis2 : R.layout.fragment_energyanalysis, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Entity entity = mValues.get(position);
        holder.mIdView.setText(entity.title);
        holder.imageView.setImageResource(entity.resId);
        if (holder.tvDesc != null) {
            holder.tvDesc.setText(entity.desc);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentActivity.start((Activity) context, entity.clazz, entity.title);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mIdView;
        ImageView imageView;
        TextView tvDesc;

        public ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.id);
            imageView = (ImageView) view.findViewById(R.id.id_item_icon);
            tvDesc = (TextView) view.findViewById(R.id.id_description);
        }
    }

    private class Entity {
        String title;
        Class clazz;
        int resId;
        String desc;

        public Entity(String title, Class clazz, int resId, String desc) {
            this.title = title;
            this.clazz = clazz;
            this.resId = resId;
            this.desc = desc;
        }
    }
}
