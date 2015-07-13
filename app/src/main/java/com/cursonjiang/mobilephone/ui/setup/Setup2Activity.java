package com.cursonjiang.mobilephone.ui.setup;

import android.os.Bundle;
import android.view.View;

import com.cursonjiang.mobilephone.R;
import com.cursonjiang.mobilephone.ui.BaseActivity;

/**
 * 设置向导页面2
 * Created by Curson on 15/7/13.
 */
public class Setup2Activity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("2.手机卡绑定");
        setContentView(R.layout.activity_setup2);
    }

    @Override
    public void next(View view) {
        intentActivity(Setup3Activity.class);
    }

    @Override
    public void pre(View view) {
        intentActivity(Setup1Activity.class);
    }

    @Override
    public void initView() {

    }


}
