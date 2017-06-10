package com.longdian.fragment.runningstate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kelin.scrollablepanel.library.PanelAdapter;
import com.longdian.R;
import com.longdian.util.DensityUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */

public class TestPanelAdapter extends PanelAdapter {

    private static final int TITLE_TYPE = 4;
    private static final int first_column_TYPE = 0;
    private static final int first_row_TYPE = 1;
    private static final int Data_TYPE = 2;

    public static final int width_type_def = 0;
    public static final int width_type_avg = 1;
    public static final int width_type_dp = 2;
    public static final int width_type_weight = 3;
    private int width_type = width_type_def;
    private List<Integer> widthValuesList;
    private int sumWidthValues;

    private List<List<String>> data;

    public TestPanelAdapter(List<List<String>> data) {
        this(data, width_type_def, null);
    }

    public TestPanelAdapter(List<List<String>> data, int widthType, List<Integer> widthValuesList) {
        this.data = data;
        width_type = widthType;
        if (widthType == width_type_dp || widthType == width_type_weight) {
            if (widthValuesList == null || widthValuesList.size() != data.get(0).size()) {
                throw new RuntimeException("参数传入错误");
            }
        }
        this.widthValuesList = widthValuesList;
        if (widthType == width_type_weight) {
            for (int i : widthValuesList) {
                sumWidthValues += i;
            }
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return data.get(0).size();
    }

    @Override
    public int getItemViewType(int row, int column) {
        if (column == 0 && row == 0) {
            return TITLE_TYPE;
        }
        if (column == 0) {
            return first_column_TYPE;
        }
        if (row == 0) {
            return first_row_TYPE;
        }
        return Data_TYPE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int row, int column) {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        changeWidth(holder.itemView.getContext(), lp, column);
        holder.itemView.setLayoutParams(lp);

        String title = data.get(row).get(column);
        TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
        titleViewHolder.titleTextView.setText(title);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case Data_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_order_info, parent, false);
                break;
            case first_column_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_room_info, parent, false);
                break;
            case first_row_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_date_info, parent, false);
                break;
            case TITLE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_title, parent, false);
                break;
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_order_info, parent, false);
                break;
        }
        return new TitleViewHolder(view);
    }

    private void changeWidth(Context context, ViewGroup.LayoutParams lp, int columnIndex) {
        switch (width_type) {
            case width_type_avg:
                lp.width = getImageItemWidth(context, getColumnCount());
                break;
            case width_type_dp:
                lp.width = DensityUtil.dp2px(context, widthValuesList.get(columnIndex));
                break;
            case width_type_weight:
                int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
                lp.width = screenWidth * widthValuesList.get(columnIndex) / sumWidthValues;
                break;
        }
    }

    private int getImageItemWidth(Context context, int columnCount) {
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        return screenWidth / columnCount;
    }

    private static class TitleViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public TitleViewHolder(View view) {
            super(view);
            this.titleTextView = (TextView) view.findViewById(R.id.title);
        }
    }
}
