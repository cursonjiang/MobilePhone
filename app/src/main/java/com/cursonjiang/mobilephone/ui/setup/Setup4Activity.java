package com.cursonjiang.mobilephone.ui.setup;

import android.os.Bundle;
import android.view.View;

import com.cursonjiang.mobilephone.R;
import com.cursonjiang.mobilephone.ui.BaseActivity;
import com.cursonjiang.mobilephone.ui.LostFindActivity;

/**
 * 设置向导页面4
 * Created by Curson on 15/7/13.
 */
public class Setup4Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("4.恭喜您,设置完成");
        setContentView(R.layout.activity_setup4);
    }

    @Override
    public void next(View view) {
        sp.edit().putBoolean("configed", true).apply();
        intentActivity(LostFindActivity.class);
    }

    @Override
    public void pre(View view) {
        intentActivity(Setup3Activity.class);
    }

    @Override
    public void initView() {

    }
}
