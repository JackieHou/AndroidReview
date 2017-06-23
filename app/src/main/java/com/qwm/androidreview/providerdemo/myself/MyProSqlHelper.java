package com.qwm.androidreview.providerdemo.myself;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.qwm.androidreview.MyApplication;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/6/23<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class MyProSqlHelper extends SQLiteOpenHelper {

    private final String CREATE_TABLE_PERSON = "create table Person(pid integer primary key autoincrement,name text,age integer)";

    public MyProSqlHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyProSqlHelper(Context context,int version) {
        super(context, "provider_test.db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERSON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(MyApplication.getIntance(), "数据库被升级了  "+oldVersion+"→"+newVersion, Toast.LENGTH_SHORT).show();
    }
}
