package com.cursonjiang.mobilephone.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 自定义TextView实现跑马灯效果
 */
public class FocusedTextView extends TextView {

    public FocusedTextView(Context context) {
        super(context);
    }

    public FocusedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FocusedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 当前并没有焦点,只是欺骗了系统.
     *
     * @return true, 让TextView一直有焦点
     */
    @Override
    public boolean isFocused() {
        return true;
    }
}
