package com.cursonjiang.mobilephone.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.cursonjiang.mobilephone.R;
import com.cursonjiang.mobilephone.ui.setup.Setup1Activity;

/**
 * 手机防盗页面
 * Created by Curson on 15/7/13.
 */
public class LostFindActivity extends BaseActivity {

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("手机防盗");
        sp = getSharedPreferences("config", MODE_PRIVATE);
        //判断用户是否已经完成过设置向导
        boolean configed = sp.getBoolean("configed", false);
        //完成过设置向导
        if (configed) {
            setContentView(R.layout.activity_lost_find);
        } else {
            //进入设置向导页面
            intentActivity(Setup1Activity.class);
            finish();
        }
    }

    /**
     * 跳到设置向导页面
     */
    public void reEntrySetup(View view) {
        intentActivity(Setup1Activity.class);
        finish();
    }

    @Override
    public void initView() {

    }
}


