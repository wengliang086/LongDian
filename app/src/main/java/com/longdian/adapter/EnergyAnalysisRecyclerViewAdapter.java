package com.longdian.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.longdian.R;
import com.longdian.activity.ContentActivity;
import com.longdian.fragment.runningstate.PieChartActivity;
import com.longdian.fragment.runningstate.TableFragment;

import java.util.Arrays;
import java.util.List;

public class EnergyAnalysisRecyclerViewAdapter extends RecyclerView.Adapter<EnergyAnalysisRecyclerViewAdapter.ViewHolder> {

    private List<Entity> mValues = Arrays.asList(
            new Entity("信息总览", PieChartActivity.class, R.drawable.ic_more_suggestion_feedback),
            new Entity("实时数据", TableFragment.class, R.drawable.ic_more_give_good_reputation),
            new Entity("历史数据", TableFragment.class, R.drawable.ic_more_rewards),
            new Entity("日报表", TableFragment.class, R.drawable.ic_more_software_update),
            new Entity("月报表", TableFragment.class, R.drawable.ic_more_friend_share),
            new Entity("年报表", TableFragment.class, R.drawable.ic_more_faq)
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
        holder.imageView.setImageResource(e.resId);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    context.startActivity(new Intent(context, e.clazz));
                } else {
                    ContentActivity.start((Activity) context, e.clazz, e.title);
                }
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

        public ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.id);
            imageView = (ImageView) view.findViewById(R.id.id_item_icon);
        }
    }

    private class Entity {
        String title;
        Class clazz;
        int resId;

        public Entity(String title, Class clazz, int resId) {
            this.title = title;
            this.clazz = clazz;
            this.resId = resId;
        }
    }
}
