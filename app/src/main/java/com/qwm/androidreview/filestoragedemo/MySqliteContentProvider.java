package com.qwm.androidreview.filestoragedemo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * @author qiwenming
 * @date 2016/4/18 17:35
 * @ClassName: MySqliteContentProvider
 * @Description:  内容提供者
 */
public class MySqliteContentProvider extends ContentProvider {

    private String TAG = MySqliteContentProvider.class.getName();

    private UriMatcher um = new UriMatcher(UriMatcher.NO_MATCH);
    private SQLiteDatabase db=null;

    private final  int PERSON_CODE = 1001;
    private final  int PERSON_ID_CODE = 1100;
    private final  int QUERY_PERSON  = 1200;
    private final  int UPDATE_PERSON = 1300;

    public MySqliteContentProvider() {
    }

    @Override
    public boolean onCreate() {
        if(db==null){
            MySQLiteOpenHelper helper =  new MySQLiteOpenHelper(getContext(),"testxm",null,1);
            db = helper.getWritableDatabase();
        }

        um.addURI("com.qwm.storage","person",PERSON_CODE);
        um.addURI("com.qwm.storage","person/#",PERSON_ID_CODE);
        Log.i(TAG, "onCreate: -----");
        return true;
    }




    @Override
    public String getType(Uri uri) {
        switch (um.match(uri)){
            case PERSON_CODE:
                return "com.qwm.storage/person";
            case PERSON_ID_CODE:
                return "com.qwm.storage/person/#";
            default:
                return "";
        }
    }

    /**
     * 增
     * @param uri
     * @param values
     * @return
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.i(TAG, "insert: ");
        switch (um.match(uri)){
            case PERSON_CODE:
                long id = db.insert("person",null,values);
//               db.close();
                return ContentUris.withAppendedId(uri,id);// 原来的 uri + id 返回
            default:
                return null;
        }
    }

    /**
     * 删
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.i(TAG, "delete: ");
        switch (um.match(uri)){
            case PERSON_CODE:
            case PERSON_ID_CODE: //这里我不对单条处理了
                int rows =db.delete("person",selection,selectionArgs);
//                db.close();
                return  rows;
            default:
                return 0;
        }
    }

    /**
     * 改
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.i(TAG, "update: ");
        switch (um.match(uri)){
            case PERSON_CODE:
            case PERSON_ID_CODE: //这里我不对单条处理了
                int rows = db.update("person",values,selection,selectionArgs);
//                db.close();
                return rows;
            default:
                return 0;
        }
    }

    /**
     * 查
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.i(TAG, "query: ");
        switch (um.match(uri)){
            case PERSON_CODE:
            case PERSON_ID_CODE: //这里我不对单条处理了
                Cursor cursor = db.query("person",projection,selection,selectionArgs,sortOrder,null,null);
//                db.close();
                return  cursor;
            default:
                return null;
        }
    }


}
