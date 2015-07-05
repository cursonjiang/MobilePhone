package com.cursonjiang.mobilephone;

import android.util.Log;

import com.cursonjiang.mobilephone.bean.BlackNumberInfo;
import com.cursonjiang.mobilephone.db.BlackNumberDBOpenHelper;
import com.cursonjiang.mobilephone.db.BlackNumberDao;

import java.util.List;
import java.util.Random;

/**
 * Created by Curson on 15/7/5.
 */
public class TestBlackNumberDB extends ApplicationTest {

    public void testCreateDB() {
        BlackNumberDBOpenHelper mHelper = new BlackNumberDBOpenHelper(App.getContext());
        mHelper.getWritableDatabase();
    }

    public void testAdd() {
        BlackNumberDao dao = new BlackNumberDao(App.getContext());
        long baseNumber = 15801339724l;
        Random random = new Random();
        for (int i = 0; i < 500; i++) {
            dao.add(String.valueOf(baseNumber + i), String.valueOf(random.nextInt(3) + 1));
        }
    }

    public void testDelete() {
        BlackNumberDao dao = new BlackNumberDao(App.getContext());
        dao.delete("15801339724");
    }

    public void testUpdate() {
        BlackNumberDao dao = new BlackNumberDao(App.getContext());
        dao.update("15801339724", "2");
    }

    public void testFind() {
        BlackNumberDao dao = new BlackNumberDao(App.getContext());
        boolean result = dao.find("15801339724");
        assertEquals(true, result);
    }

    public void testFindAll() {
        BlackNumberDao dao = new BlackNumberDao(App.getContext());
        List<BlackNumberInfo> infos = dao.findAll();
        for (BlackNumberInfo info : infos) {
            Log.d("查询全部", info.toString());
        }
    }
}
