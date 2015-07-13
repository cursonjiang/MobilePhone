package com.cursonjiang.mobilephone.ui.setup;

import android.os.Bundle;
import android.view.View;

import com.cursonjiang.mobilephone.R;
import com.cursonjiang.mobilephone.ui.BaseActivity;

/**
 * 设置向导页面3
 * Created by Curson on 15/7/13.
 */
public class Setup3Activity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("3.设置安全号码");
        setContentView(R.layout.activity_setup3);
    }

    @Override
    public void next(View view) {
        intentActivity(Setup4Activity.class);
    }

    @Override
    public void pre(View view) {
        intentActivity(Setup2Activity.class);
    }

    @Override
    public void initView() {

    }
}
