package com.cursonjiang.mobilephone;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Curson on 15/7/5.
 */
public class App extends Application {

    private static Context mContext;


    private static List<Activity> mActivities = new LinkedList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Logger.init();
    }

    public static Context getContext() {
        return mContext;
    }

    public static void addActivity(Activity activity) {
        mActivities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        mActivities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : mActivities) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

}
