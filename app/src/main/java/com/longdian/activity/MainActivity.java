package com.longdian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.longdian.R;
import com.longdian.fragment.BaseMsgFragment;
import com.longdian.fragment.CloudServiceFragment;
import com.longdian.fragment.EnergyAnalysisFragment;
import com.longdian.fragment.RealtimeDataFragment;
import com.longdian.util.ToastUtils;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, EnergyAnalysisFragment.OnListFragmentInteractionListener {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.id_main_rg);
        radioGroup.setOnCheckedChangeListener(this);
        ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }

    public void onListFragmentInteraction(String string) {
        ToastUtils.showToast(this, string);
        startActivity(new Intent(this, PieChartActivity.class));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.id_energy_analysis:
                setTitle(R.string.main_navigation_energy_analysis);
                replaceFragment(EnergyAnalysisFragment.newInstance(1));
                break;
            case R.id.id_realtime_data:
                setTitle(R.string.main_navigation_realtime_data);
                replaceFragment(new RealtimeDataFragment());
                break;
            case R.id.id_cloud_service:
                setTitle(R.string.main_navigation_cloud_service);
                replaceFragment(new CloudServiceFragment());
                break;
            case R.id.id_base_msg:
                setTitle(R.string.main_navigation_base_msg);
                replaceFragment(new BaseMsgFragment());
                break;
        }
    }
}
