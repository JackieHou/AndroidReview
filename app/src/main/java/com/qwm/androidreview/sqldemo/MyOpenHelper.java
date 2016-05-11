package com.qwm.androidreview.sqldemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyOpenHelper extends SQLiteOpenHelper {


    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
    }

    //数据库被创建的时候此方法调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table person(_id integer primary key autoincrement, name char(10)," +
                "phone char(20), money integer(20))");
    }

    //数据库版本升级的时候此方法调用
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        System.out.println("数据库被升级了");
    }


}