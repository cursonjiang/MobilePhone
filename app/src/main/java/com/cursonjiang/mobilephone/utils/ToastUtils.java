package com.cursonjiang.mobilephone.utils;

import android.content.Context;
import android.widget.Toast;

import com.cursonjiang.mobilephone.App;

/**
 * Created by Curson on 15/7/13.
 */
public class ToastUtils {

    private static Toast mToast;

    public static void showToast(Context context, CharSequence text, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(App.getContext(), text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }
        mToast.show();
    }
}
