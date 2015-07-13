package com.cursonjiang.mobilephone.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cursonjiang.mobilephone.App;

/**
 * Created by root on 15/6/13.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected SharedPreferences sp;

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.addActivity(this);
        mActionBar = getSupportActionBar();
        sp = getSharedPreferences("config", MODE_PRIVATE);
    }

    public void setTitle(int resId) {
        mActionBar.setTitle(resId);
    }

    public void setTitle(CharSequence text) {
        mActionBar.setTitle(text);
    }

    public void setActionBarHide() {
        mActionBar.hide();
    }

    protected void intentActivity(Class<? extends Activity> activity) {
        startActivity(new Intent(App.getContext(), activity));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.removeActivity(this);
    }

    /**
     * 控件初始化
     */
    public abstract void initView();

    /**
     * 下一步的点击事件
     */
    public abstract void next(View view);

    /**
     * 上一步的点击事件
     */
    public abstract void pre(View view);
}
