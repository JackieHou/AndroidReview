package com.qwm.androidreview.filestoragedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author qiwenming
 * @date 2016/4/18 16:38
 * @ClassName: MySQLiteHelper
 * @Description:  数据库类
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private String TAG = MySQLiteOpenHelper.class.getName();

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL("CREATE TABLE person  (_id INTEGER PRIMARY KEY AUTOINCREMENT,name CHAR(20) ,age INTEGER)");
        Log.i(TAG, "onCreate: 表被创建了");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade: 数据库被升级了");
    }
}
