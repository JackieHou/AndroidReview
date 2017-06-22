package com.qwm.androidreview.datastoragedemo.sqlitestorage;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * <b>Project:</b> YuantaiApplication<br>
 * <b>Create Date:</b> 2017/6/16<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b>
 * Sqlite
 * <br>
 */
public class SqliteStorageActivity extends BaseActivity {

    @Bind(R.id.sqlite_gv)
    GridView sqliteGv;
    @Bind(R.id.sqlite_lv)
    ListView sqliteLv;

    private static final String TAG = "SqliteStorageActivity";

    private MySQLiteOpenHelper mHelper;
    private SQLiteDatabase db;

    private DataAdapter mDataAdapter;
    private TableNamesAdaper mTableNamesAdapter;

    private List<String> mButtonTexts = Arrays.asList(
            "查询所有表","升级","增加","删除","修改","查询","删除升级表"
    );
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_storage);
        ButterKnife.bind(this);
        sp = getSharedPreferences("SharedPreferencesActivity",0);
        mHelper = new MySQLiteOpenHelper(this,"sqlite_xm_text",null,getVersion());
        db = mHelper.getReadableDatabase();
        mDataAdapter = new DataAdapter(this,null);
         mTableNamesAdapter = new TableNamesAdaper(this,null);
        initGv();
    }

    private void initGv() {
        ButtonAdapter buttonAdapter = new ButtonAdapter(this,mButtonTexts);
        sqliteGv.setAdapter(buttonAdapter);
        sqliteGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemStr = mButtonTexts.get(position);
                if("查询所有表".equals(itemStr)){
                    queryAllTables();
                }else  if("升级".equals(itemStr)){
                    update();
                }else if("增加".equals(itemStr)){
                    addData();
                }else if("删除".equals(itemStr)){
                    deleteData();
                }else if("修改".equals(itemStr)){
                    updateData();
                }else if("查询".equals(itemStr)){
                    queryData();
                }else if("删除升级表".equals(itemStr)){
                    dropUpdateTable();
                }
            }
        });
    }

    private void queryAllTables() {
        String sql = "select name from sqlite_master where type='table'";
        Cursor cursor =db.rawQuery(sql,null);
        cursor.moveToFirst();
        List<String> list = new ArrayList<>();
        boolean isNext = cursor.moveToNext();
        while (isNext){
            String name = cursor.getString(0);
            Log.i(TAG, "----"+ name);
            list.add(name);
            isNext = cursor.moveToNext();
        }
        mTableNamesAdapter.resetData(list);
        sqliteLv.setAdapter(mTableNamesAdapter);
    }

    private void update() {
        int version = getVersion() +1;
        mHelper = new MySQLiteOpenHelper(this,"sqlite_xm_text",null,version);
        db = mHelper.getReadableDatabase();
        setVersion(version);
        queryAllTables();
    }

    private void addData() {
        ContentValues cv = new ContentValues();
        cv.put("name","xm_"+(System.currentTimeMillis()%10000));
        cv.put("age",(int)(System.currentTimeMillis()%100));
        db.insert("Person",null,cv);
        queryData();
    }

    private void deleteData() {
        db.delete("Person",null,null);
        queryData();
    }

    private void updateData() {
        ContentValues cv = new ContentValues();
        cv.put("age",System.currentTimeMillis()%100);
        db.update("Person",cv,"name like '%6%'",null);//包含6的修改
        queryData();
    }

    private void queryData() {
        Cursor c = db.query("Person",new String[]{"name","age"},null,null,null,null,null);
        boolean isNext = c.moveToFirst();
        List<PersonBean> list = new ArrayList<>();
        while(isNext){
            PersonBean bean = new PersonBean();
            bean.name = c.getString(0);
            bean.age = c.getInt(1);
            list.add(bean);
            Log.i(TAG, "queryData: "+bean);
            isNext = c.moveToNext();
        }
        //刷新列表
        mDataAdapter.resetData(list);
        sqliteLv.setAdapter(mDataAdapter);
    }

    private void dropUpdateTable() {
        //查询需要删除的表
        String sql = "select name from sqlite_master where type='table' and name like 'Test%'";
        StringBuilder sb = new StringBuilder();
        Cursor c = db.rawQuery(sql,null);
        boolean isNext = c.moveToFirst();
        while(isNext){
            String name = c.getString(0);
            Log.i(TAG, "dropUpdateTable: --"+name);
            sb.append("drop table "+name+";");
            isNext = c.moveToNext();
        }
        String dropSql = sb.toString();
        Log.i(TAG, "dropUpdateTable: dropSql:--"+dropSql);
        if(!TextUtils.isEmpty(dropSql)){
            db.execSQL(dropSql);
        }
        queryAllTables();
    }

    //标的版本我们使用一个
    public int getVersion(){
        return sp.getInt("version",1);
    }

    public void setVersion(int version){
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("version",version);
        editor.commit();
    }
}
