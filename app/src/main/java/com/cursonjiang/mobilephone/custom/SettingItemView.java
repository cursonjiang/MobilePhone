package com.cursonjiang.mobilephone.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cursonjiang.mobilephone.R;

/**
 * 自定义组合控件
 * 含有两个TextView,一个CheckBox,还有一个View
 */
public class SettingItemView extends RelativeLayout {

    private CheckBox cb_status;
    private TextView tv_desc;
    private TextView tv_title;


    public SettingItemView(Context context) {
        super(context);
        initView(context);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        //把一个布局文件转换成View,并加载在SettingItemView
        View.inflate(context, R.layout.setting_item_view, this);
        cb_status = (CheckBox) this.findViewById(R.id.cb_status);
        tv_desc = (TextView) this.findViewById(R.id.tv_desc);
        tv_title = (TextView) this.findViewById(R.id.tv_title);
    }

    /**
     * 校验组合控件是否有焦点
     *
     * @return CheckBox的状态
     */
    public boolean isChecked() {
        return cb_status.isChecked();
    }

    /**
     * 设置组合控件的状态
     *
     * @param checked 是否点击
     */
    public void setChecked(boolean checked) {
        cb_status.setChecked(checked);
    }

    /**
     * 设置组合控件的描述信息
     *
     * @param text 描述信息
     */
    public void setDesc(String text) {
        tv_desc.setText(text);
    }
}
