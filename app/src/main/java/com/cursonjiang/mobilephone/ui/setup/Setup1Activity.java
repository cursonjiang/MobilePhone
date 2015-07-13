package com.cursonjiang.mobilephone.ui.setup;

import android.os.Bundle;
import android.view.View;

import com.cursonjiang.mobilephone.R;
import com.cursonjiang.mobilephone.ui.BaseActivity;

/**
 * 设置向导页面1
 * Created by Curson on 15/7/13.
 */
public class Setup1Activity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("1.欢迎使用手机防盗");
        setContentView(R.layout.activity_setup1);
    }

    @Override
    public void next(View view) {
        intentActivity(Setup2Activity.class);
    }

    @Override
    public void pre(View view) {

    }

    @Override
    public void initView() {

    }

}
