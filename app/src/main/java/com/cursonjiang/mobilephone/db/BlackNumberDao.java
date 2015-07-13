package com.cursonjiang.mobilephone.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cursonjiang.mobilephone.bean.BlackNumberInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 黑名单数据库的曾删改差业务类
 * Created by Curson on 15/7/5.
 */
public class BlackNumberDao {

    private BlackNumberDBOpenHelper mHelper;

    /**
     * 构造方法 获取数据库对象
     *
     * @param context 上下文
     */
    public BlackNumberDao(Context context) {
        mHelper = new BlackNumberDBOpenHelper(context);
    }

    /**
     * 查询黑名单号码是否存在
     *
     * @param number 拦截号码
     * @return result
     */
    public boolean find(String number) {
        boolean result = false;
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from blacknumber where number = ?", new String[]{number});
        if (cursor.moveToNext()) {
            result = true;
        }
        cursor.close();
        db.close();
        return result;
    }

    /**
     * 查询全部黑名单号码
     */
    public List<BlackNumberInfo> findAll() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<BlackNumberInfo> result = new ArrayList<>();
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select number,mode from blacknumber order by _id desc", null);
        while (cursor.moveToNext()) {
            BlackNumberInfo info = new BlackNumberInfo();
            String number = cursor.getString(0);
            String mode = cursor.getString(1);
            info.setNumber(number);
            info.setMode(mode);
            result.add(info);
        }
        cursor.close();
        db.close();
        return result;
    }

    /**
     * 查询部分的黑名单好吗
     *
     * @param startIndex 从哪个位置开始获取数据
     * @param maxNumber  一次最多获取多少条记录
     * @return result集合
     */
    public List<BlackNumberInfo> findPart(int startIndex, int maxNumber) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<BlackNumberInfo> result = new ArrayList<>();
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "select number,mode from blacknumber order by _id desc limit ? offset ?",
                new String[]{
                        String.valueOf(maxNumber),
                        String.valueOf(startIndex)
                }
        );
        while (cursor.moveToNext()) {
            BlackNumberInfo info = new BlackNumberInfo();
            String number = cursor.getString(0);
            String mode = cursor.getString(1);
            info.setNumber(number);
            info.setMode(mode);
            result.add(info);
        }
        cursor.close();
        db.close();
        return result;
    }

    /**
     * 返回一共有多少数据
     *
     * @return count 数据总数
     */
    public int findTotalNumber() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select number,mode from blacknumber order by _id desc", null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }

    /**
     * 添加黑名单号码
     *
     * @param number 拦截号码
     * @param mode   拦截模式 1.电话拦截 2.短信拦截 3.全部拦截
     */
    public void add(String number, String mode) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("number", number);
        values.put("mode", mode);
        db.insert("blacknumber", null, values);
        db.close();
    }

    /**
     * 修改黑名单号码的拦截模式
     *
     * @param newMode 拦截模式 1.电话拦截 2.短信拦截 3.全部拦截
     */
    public void update(String number, String newMode) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mode", newMode);
        db.update("blacknumber", values, "number=?", new String[]{number});
        db.close();
    }

    /**
     * 删除黑名单好吗
     *
     * @param number 黑名单号码
     */
    public void delete(String number) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete("blacknumber", "number=?", new String[]{number});
        db.close();
    }

}
