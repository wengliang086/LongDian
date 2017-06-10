package com.longdian.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longdian.R;
import com.longdian.activity.ContentActivity;
import com.longdian.fragment.runningstate.PieChartActivity;
import com.longdian.fragment.runningstate.TableFragment;

import java.util.Arrays;
import java.util.List;

public class EnergyAnalysisRecyclerViewAdapter extends RecyclerView.Adapter<EnergyAnalysisRecyclerViewAdapter.ViewHolder> {

    private List<Entity> mValues = Arrays.asList(
            new Entity("信息总览", PieChartActivity.class),
            new Entity("实时数据", TableFragment.class),
            new Entity("历史数据", TableFragment.class),
            new Entity("日报表", TableFragment.class),
            new Entity("月报表", TableFragment.class),
            new Entity("年报表", TableFragment.class)
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
        final Entity e = mValues.get(position);
        holder.mIdView.setText(e.title);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    context.startActivity(new Intent(context, e.clazz));
                } else {
                    ContentActivity.start((Activity) context, e.clazz);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
        }
    }

    private class Entity {
        String title;
        Class clazz;

        public Entity(String title, Class clazz) {
            this.title = title;
            this.clazz = clazz;
        }
    }
}
