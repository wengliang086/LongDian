package com.longdian.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.longdian.R;
import com.longdian.util.LogUtil;

public class ContentActivity extends AppCompatActivity {

    public static final String FragmentClassName = "FragmentClassName";
    private FragmentManager fragmentManager;

    public static void start(Activity activity, Class clazz) {
        Intent intent = new Intent(activity, ContentActivity.class);
        intent.putExtra(FragmentClassName, clazz);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        fragmentManager = getSupportFragmentManager();
        Class clazz = (Class) getIntent().getSerializableExtra(FragmentClassName);
        try {
            replaceFragment((Fragment) clazz.newInstance());
        } catch (Exception e) {
            LogUtil.e(e);
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }
}
