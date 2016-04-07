package com.qwm.androidreview.providerdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.qwm.androidreview.sqldemo.MyOpenHelper;

/** @author qiwenming
 * @date 2016/3/29 16:33
 * @ClassName: PersonProvider
 * @Description: 个人的内容提供者
 */
public class PersonProvider extends ContentProvider {

    MyOpenHelper oh;
    SQLiteDatabase db;

    //用来检测传入的uri在指定的多条uri中，与哪条匹配
    UriMatcher um = new UriMatcher(UriMatcher.NO_MATCH);
    {
        //指定uri
        um.addURI("com.qwm.androidreview.providerdemo.PersonProvider", "person", 1);//content://com.qwm.xiaoming/person
//        um.addURI("com.qwm.xiaoming", "person/#", 3);//content://com.qwm.xiaoming/person/10
//        um.addURI("com.qwm.xiaoming", "teacher", 2);//content://com.qwm.xiaoming/teacher

    }

    //内容提供者创建时会调用
    @Override
    public boolean onCreate() {
        //                  区别于测试框架的getContext()
        oh = new MyOpenHelper(getContext(), "people.db", null, 3);
        db = oh.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }


    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if(um.match(uri) == 1){
            db.insert("person", null, values);
        }
//        else if(um.match(uri) == 2){
//            db.insert("teacher", null, values);
//        }
        else{
            throw new IllegalArgumentException();
        }
        return uri;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
