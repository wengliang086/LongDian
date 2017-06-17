package com.longdian.fragment;

import android.app.DatePickerDialog;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.longdian.R;

import java.util.Calendar;

/**
 * Created by phoenix on 2017/6/17.
 */

public abstract class BaseDatePickerFragment extends Fragment implements View.OnClickListener, View.OnFocusChangeListener {

    protected TextView textViewStart;
    protected TextView textViewEnd;
    private boolean firstTag = true;// 第一次不弹出

    protected void initDatePicker(View baseView) {
        textViewStart = (TextView) baseView.findViewById(R.id.id_date_start);
        textViewStart.setOnClickListener(this);
        textViewStart.setOnFocusChangeListener(this);
        textViewEnd = (TextView) baseView.findViewById(R.id.id_date_end);
        textViewEnd.setOnClickListener(this);
        textViewEnd.setOnFocusChangeListener(this);
        baseView.findViewById(R.id.id_search).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_date_start:
                showDatePickerDialog(textViewStart);
                break;
            case R.id.id_date_end:
                showDatePickerDialog(textViewEnd);
                break;
            case R.id.id_search:
                doSearch();
                break;
        }
    }

    protected abstract void doSearch();

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (firstTag) {
            firstTag = false;
            return;
        }
        if (!hasFocus) {
            return;
        }
        switch (v.getId()) {
            case R.id.id_date_start:
                showDatePickerDialog(textViewStart);
                break;
            case R.id.id_date_end:
                showDatePickerDialog(textViewEnd);
                break;
        }
    }

    private void showDatePickerDialog(final TextView textView) {
        String dateStr = textView.getText().toString();
        String[] array = dateStr.split("-");

        Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        if (array.length == 3) {
            year = Integer.parseInt(array[0]);
            month = Integer.parseInt(array[1]) - 1;
            dayOfMonth = Integer.parseInt(array[2]);
        }
        DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                textView.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        }, year, month, dayOfMonth);
        dialog.show();
    }
}
