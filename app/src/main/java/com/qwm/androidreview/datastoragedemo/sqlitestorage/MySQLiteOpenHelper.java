package com.qwm.androidreview.datastoragedemo.sqlitestorage;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.qwm.androidreview.MyApplication;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/6/21<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private final String CREATE_TABLE_PERSON = "create table Person(id integer primary key autoincrement,name text,age integer)";

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERSON);
        Toast.makeText(MyApplication.getIntance(), "创建了数据库", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table Test"+newVersion+"(name text)");
        Toast.makeText(MyApplication.getIntance(), "升级数据库啦："+oldVersion+" → "+newVersion, Toast.LENGTH_SHORT).show();
    }

}
