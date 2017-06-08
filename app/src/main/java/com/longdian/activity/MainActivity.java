package com.longdian.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.longdian.R;
import com.longdian.fragment.BaseMsgFragment;
import com.longdian.fragment.CloudServiceFragment;
import com.longdian.fragment.EnergyAnalysisFragment;
import com.longdian.fragment.RealtimeDataFragment;
import com.longdian.util.ToastUtils;

public class MainActivity extends TopBarBaseActivity implements RadioGroup.OnCheckedChangeListener, EnergyAnalysisFragment.OnListFragmentInteractionListener {

    private FragmentManager fragmentManager;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.id_main_rg);
        radioGroup.setOnCheckedChangeListener(this);

        setRadioButtonTopDrawableSize(R.id.id_energy_analysis, R.drawable.selector_main_button_find);
        setRadioButtonTopDrawableSize(R.id.id_realtime_data, R.drawable.selector_main_button_subscribe);
        setRadioButtonTopDrawableSize(R.id.id_cloud_service, R.drawable.selector_main_button_download);
        setRadioButtonTopDrawableSize(R.id.id_base_msg, R.drawable.selector_main_button_mine);

        ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
    }

    /**
     * 解决不能控制RadioButton图片大小的问题
     *
     * @param id
     * @param drawableId
     */
    private void setRadioButtonTopDrawableSize(int id, int drawableId) {
        RadioButton radioButton = (RadioButton) findViewById(id);
        Drawable drawable = getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, 60, 60);
        radioButton.setCompoundDrawables(null, drawable, null, null);
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

    private boolean isGrid = false;// 界面切换标志

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.id_energy_analysis:
                setTitle(getString(R.string.main_navigation_energy_analysis));
                setTopRightButton("buttion", R.drawable.ic_mine_white_24dp, new OnClickListener() {
                    @Override
                    public void onClick() {
                        replaceFragment(EnergyAnalysisFragment.newInstance(isGrid ? 1 : 3));
                        updateMenuItemIcon(isGrid ? R.drawable.ic_mine_white_24dp : R.drawable.ic_return_white_24dp);
                        isGrid = !isGrid;
                    }
                });
                updateMenuItemIcon(isGrid ? R.drawable.ic_mine_white_24dp : R.drawable.ic_return_white_24dp);
                replaceFragment(EnergyAnalysisFragment.newInstance(1));
                break;
            case R.id.id_realtime_data:
                setTitle(getString(R.string.main_navigation_realtime_data));
                hideRight();
                replaceFragment(new RealtimeDataFragment());
                break;
            case R.id.id_cloud_service:
                setTitle(getString(R.string.main_navigation_cloud_service));
                hideRight();
                replaceFragment(new CloudServiceFragment());
                break;
            case R.id.id_base_msg:
                setTitle(getString(R.string.main_navigation_base_msg));
                hideRight();
                replaceFragment(new BaseMsgFragment());
                break;
        }
    }
}
