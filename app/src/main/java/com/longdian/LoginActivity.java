package com.longdian;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;

import com.longdian.activity.MainActivity;
import com.longdian.bean.GlobalInfo;
import com.longdian.bean.OprInfo;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.PreferencesUtils;
import com.longdian.util.SystemBarTintManager;
import com.longdian.util.ToastUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    public static final String SAVE_PWD = "savePwd";
    public static final String AUTO_LOGIN = "autoLogin";
    public static final String SAVED_ACCOUNT = "savedAccount";
    public static final String SAVED_PWD = "savedPwd";
    private CheckBox savePwd;
    private CheckBox autoLogin;
    private TextView tvAccount;
    private TextView tvPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (2 > 3) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            return;
        }
        setContentView(R.layout.activity_login);
//        getSupportActionBar().hide();
        ButterKnife.bind(this);
        setStatusBarTranslucent(this, -1);
        // 初始化
        savePwd = (CheckBox) findViewById(R.id.id_save_pwd);
        autoLogin = (CheckBox) findViewById(R.id.id_auto_login);
        tvAccount = (TextView) findViewById(R.id.id_phone);
        tvPwd = (TextView) findViewById(R.id.id_pwd);

        boolean isSavePwd = PreferencesUtils.getBoolean(this, SAVE_PWD);
        boolean isAutoLogin = PreferencesUtils.getBoolean(this, AUTO_LOGIN);
        String savedAccount = PreferencesUtils.getString(this, SAVED_ACCOUNT);
        String savedPwd = PreferencesUtils.getString(this, SAVED_PWD);

        savePwd.setChecked(isSavePwd);
        autoLogin.setChecked(isAutoLogin);
        tvAccount.setText(savedAccount);
        if (isSavePwd) {
            tvPwd.setText(savedPwd);
        }
        if (isAutoLogin) {
            Login();
        }
    }

    @OnClick(R.id.id_submit)
    public void Login() {
        String account = tvAccount.getText().toString();
        String pwd = tvPwd.getText().toString();
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(pwd)) {
            ToastUtils.showToast(this, "账号或密码错误");
            return;
        }
        doLogin(account, pwd);
    }

    public void doLogin(final String account, final String pwd) {
        HoolaiHttpMethods.getInstance().login(this, account, pwd, new ObserverOnNextAndErrorListener<OprInfo>() {
            @Override
            public void onNext(OprInfo oprInfo) {
                GlobalInfo.oprInfo = oprInfo;

                PreferencesUtils.putBoolean(LoginActivity.this, SAVE_PWD, savePwd.isChecked());
                PreferencesUtils.putBoolean(LoginActivity.this, AUTO_LOGIN, autoLogin.isChecked());
                PreferencesUtils.putString(LoginActivity.this, SAVED_ACCOUNT, account);
                PreferencesUtils.putString(LoginActivity.this, SAVED_PWD, pwd);

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onError(HoolaiException e) {
                ToastUtils.showToast(LoginActivity.this, e.getMessage());
            }
        });
    }

    public static void setStatusBarTranslucent(Activity activity, int colorRes) {
        // 如果版本在4.4以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (colorRes > 0) {
                SystemBarTintManager tintManager = new SystemBarTintManager(activity);
                tintManager.setStatusBarTintEnabled(true);
                tintManager.setStatusBarTintResource(colorRes);
            }
        }
    }

    public static void clearAutoLogin(Activity activity) {
        PreferencesUtils.putBoolean(activity, AUTO_LOGIN, false);
    }

    public static void updatePwd(Activity activity, String pwd) {
        PreferencesUtils.putString(activity, SAVED_PWD, pwd);
    }
}
