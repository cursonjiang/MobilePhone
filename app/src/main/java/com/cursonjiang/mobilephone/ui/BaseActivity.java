package com.cursonjiang.mobilephone.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by root on 15/6/13.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionBar = getSupportActionBar();
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

    public abstract void initView();

}
