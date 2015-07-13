package com.cursonjiang.mobilephone.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 黑名单数据库帮助类
 * Created by Curson on 15/7/5.
 */
public class BlackNumberDBOpenHelper extends SQLiteOpenHelper {

    /**
     * 数据库创建的构造方法
     *
     * @param context 上下文
     */
    public BlackNumberDBOpenHelper(Context context) {
        super(context, "blacknumber.db", null, 1);
    }

    /**
     * 创建表
     *
     * @param db 数据库对象
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table blacknumber (_id integer primary key autoincrement,number varchar(20),mode varchar(2))");
    }

    /**
     * 升级数据库
     *
     * @param db         数据库对象
     * @param oldVersion 旧版本
     * @param newVersion 新版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
