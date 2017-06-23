package com.qwm.androidreview.providerdemo.myself;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.qwm.androidreview.MyApplication;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/6/23<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class PersonProvider2 extends ContentProvider {
    private SQLiteDatabase db;
    private final int PERSON_MUL = 1;//多个
    private final int PERSON_SINGLE = 2;//一个

    private UriMatcher um = new UriMatcher(UriMatcher.NO_MATCH);
    private MyProSqlHelper helper;

    {
        um.addURI("com.qwm.androidreview.providerdemo.myself.PersonProvider2","person",PERSON_MUL);  //content://com.qwm.androidreview.providerdemo.myself.PersonProvider2/person
        um.addURI("com.qwm.androidreview.providerdemo.myself.PersonProvider2","person/#",PERSON_SINGLE);
    }

    @Override
    public boolean onCreate() {
        helper = new MyProSqlHelper(getContext(),1);
        db = helper.getReadableDatabase();
        return false;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        if(um.match(uri) == PERSON_MUL)
            return "vnd.android.cursor.dir";
        if(um.match(uri) == PERSON_SINGLE)
            return "vnd.android.cursor.item";
        return null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if(um.match(uri)==PERSON_MUL){
            return db.query("person",projection,selection,selectionArgs,null,null,sortOrder);
        }else if(um.match(uri)==PERSON_SINGLE){
            long id = (int) ContentUris.parseId(uri);
            return db.query("person",projection,"pid = ?",new String[]{id+""},null,null,sortOrder);
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if(um.match(uri)==PERSON_MUL){
            db.insert("person",null,values);
        }else{
            throw new IllegalArgumentException();
        }
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if(um.match(uri)==PERSON_MUL){
            return db.delete("person",selection,selectionArgs);
        }else if(um.match(uri)==PERSON_SINGLE){
            long id = (int) ContentUris.parseId(uri);
            return db.delete("person","pid = ?",new String[]{id+""});
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if(um.match(uri)==PERSON_MUL){
            return db.update("person",values,selection,selectionArgs);
        }else if(um.match(uri)==PERSON_SINGLE){
            long id = (int) ContentUris.parseId(uri);
            return db.update("person",values,"pid = ?",new String[]{id+""});
        }
        return 0;
    }
}
