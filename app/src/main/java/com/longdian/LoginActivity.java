package com.longdian;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.longdian.activity.MainActivity;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.ToastUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getSupportActionBar().hide();
        ButterKnife.bind(this);
        setStatusBarTranslucent(this);
    }

    @OnClick(R.id.id_submit)
    public void Login() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @OnClick(R.id.id_test)
    public void test() {
        HoolaiHttpMethods.getInstance().test(this, "dfaf", new ObserverOnNextAndErrorListener<String>() {
            @Override
            public void onNext(String s) {
                ToastUtils.showToast(LoginActivity.this, s);
            }

            @Override
            public void onError(HoolaiException e) {
                ToastUtils.showToast(LoginActivity.this, e.getMessage());
            }
        });
    }

    public static void setStatusBarTranslucent(Activity activity) {
        // 如果版本在4.4以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
