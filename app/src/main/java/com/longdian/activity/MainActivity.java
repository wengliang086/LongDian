package com.longdian.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.longdian.R;
import com.longdian.fragment.BaseMsgFragment;
import com.longdian.fragment.CloudServiceFragment;
import com.longdian.fragment.EnergyAnalysisFragment;
import com.longdian.fragment.RealtimeDataFragment;
import com.longdian.util.ToastUtils;

public class MainActivity extends AppCompatActivity implements EnergyAnalysisFragment.OnListFragmentInteractionListener {

    private FragmentManager fragmentManager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.id_energy_analysis:
                    setTitle(R.string.main_navigation_energy_analysis);
                    replaceFragment(EnergyAnalysisFragment.newInstance(1));
                    return true;
                case R.id.id_realtime_data:
                    setTitle(R.string.main_navigation_realtime_data);
                    replaceFragment(new RealtimeDataFragment());
                    return true;
                case R.id.id_cloud_service:
                    setTitle(R.string.main_navigation_cloud_service);
                    replaceFragment(new CloudServiceFragment());
                    return true;
                case R.id.id_base_msg:
                    setTitle(R.string.main_navigation_base_msg);
                    replaceFragment(new BaseMsgFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        replaceFragment(EnergyAnalysisFragment.newInstance(1));

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }

    @Override
    public void onListFragmentInteraction(String string) {
        ToastUtils.showToast(this, string);
    }
}
