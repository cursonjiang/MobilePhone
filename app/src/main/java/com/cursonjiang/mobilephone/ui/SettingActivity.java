package com.cursonjiang.mobilephone.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.cursonjiang.mobilephone.R;
import com.cursonjiang.mobilephone.custom.SettingItemView;

/**
 * 设置页面
 */
public class SettingActivity extends BaseActivity {

    private SettingItemView siv_update;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        setTitle("设置中心");
        mSharedPreferences = getSharedPreferences("config", MODE_PRIVATE);

        //获取自动升级的状态
        boolean update = mSharedPreferences.getBoolean("update", false);
        if (update) {
            //自动升级已经打开
            siv_update.setChecked(true);
            siv_update.setDesc("自动更新已经打开");
        } else {
            //自动升级已经关闭
            siv_update.setChecked(false);
            siv_update.setDesc("自动更新已经关闭");
        }

        siv_update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor edit = mSharedPreferences.edit();
                        if (siv_update.isChecked()) {
                            //关闭了自动升级
                            siv_update.setChecked(false);
                            siv_update.setDesc("自动更新已经关闭");
                            edit.putBoolean("update", false);
                        } else {
                            //已经打开了自动升级
                            siv_update.setChecked(true);
                            siv_update.setDesc("自动更新已经打开");
                            edit.putBoolean("update", true);
                        }
                        edit.apply();
                    }
                }
        );
    }

    @Override
    public void initView() {
        siv_update = (SettingItemView) findViewById(R.id.siv_update);
    }
}
