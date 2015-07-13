package com.cursonjiang.mobilephone.ui;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.cursonjiang.mobilephone.R;

/**
 * 启动页面
 */
public class SplashActivity extends BaseActivity {

    private TextView tv_splash_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setActionBarHide();
        initView();
        initData();

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        enterHome();
                    }
                }, 2000
        );
    }

    private void enterHome() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, 1.0f);
        alphaAnimation.setDuration(500);
        findViewById(R.id.rl_root_splash).startAnimation(alphaAnimation);
        intentActivity(HomeActivity.class);
        finish();
    }

    private void initData() {
        tv_splash_version.setText("版本号:" + getVersionName());
    }

    @Override
    public void initView() {
        tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);
    }


    /**
     * 得到应用程序的版本名称
     *
     * @return packageInfo.versionName
     */
    private String getVersionName() {
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 拦截返回键
     *
     * @return true
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK || super.onKeyDown(keyCode, event);
    }
}
