package com.longdian.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.longdian.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.id_energy_analysis:
                    setTitle(R.string.main_navigation_energy_analysis);
                    return true;
                case R.id.id_realtime_data:
                    setTitle(R.string.main_navigation_realtime_data);
                    return true;
                case R.id.id_cloud_service:
                    setTitle(R.string.main_navigation_cloud_service);
                    return true;
                case R.id.id_base_msg:
                    setTitle(R.string.main_navigation_base_msg);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
