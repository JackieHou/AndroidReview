package com.qwm.androidreview.providerdemo.myself;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;
import com.qwm.androidreview.datastoragedemo.sqlitestorage.ButtonAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * <b>Project:</b> androidreview<br>
 * <b>Create Date:</b> 2017/6/22<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class ProviderMySelfActivity extends BaseActivity {

    private static final String TAG = "ProviderMySelfActivity";

    @Bind(R.id.provider_my_sql_gv)
    GridView providerMySqlGv;
    @Bind(R.id.provider_my_pro_gv)
    GridView providerMyProGv;
    @Bind(R.id.provider_my_lv)
    ListView providerMyLv;
    @Bind(R.id.provider_my_pro_edt)
    EditText providerMyProEdt;

    private List<String> mSqlBtns = Arrays.asList(
            "增", "删", "改", "查"
    );
    private List<String> mProBtns = Arrays.asList(
            "增", "删", "改", "查", "根据id查询"
    );
    private SQLiteDatabase db;
    private PersonAdapter mAdapter;
    private ContentResolver provider;

    private final String PROVIDER_CONTENT_AUTHORITES = "content://com.qwm.androidreview.providerdemo.myself.PersonProvider2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_my_self);
        ButterKnife.bind(this);
        db = new MyProSqlHelper(this, 1).getReadableDatabase();
        provider = getContentResolver();
        mAdapter = new PersonAdapter(this, null);
        initGv();
        providerMyLv.setAdapter(mAdapter);
    }

    private void initGv() {
        ButtonAdapter buttonAdapter = new ButtonAdapter(this, mSqlBtns);
        providerMySqlGv.setAdapter(buttonAdapter);
        providerMySqlGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemStr = mSqlBtns.get(position);
                if ("增".equals(itemStr)) {
                    insertBySql();
                } else if ("删".equals(itemStr)) {
                    deleteSql();
                } else if ("改".equals(itemStr)) {
                    updateSql();
                } else if ("查".equals(itemStr)) {
                    querySql();
                }
            }
        });

        ButtonAdapter buttonAdapter2 = new ButtonAdapter(this, mProBtns);
        providerMyProGv.setAdapter(buttonAdapter2);
        providerMyProGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemStr = mProBtns.get(position);
                if ("增".equals(itemStr)) {
                    insertByPro();
                } else if ("删".equals(itemStr)) {
                    deletePro();
                } else if ("改".equals(itemStr)) {
                    updatePro();
                } else if ("查".equals(itemStr)) {
                    queryPro();
                } else if ("根据id查询".equals(itemStr)) {
                    queryProById();
                }
            }
        });
    }

    //=====================================Sqlite=============================

    private void insertBySql() {
        ContentValues cv = new ContentValues();
        for (int i = 0; i < 10; i++) {
            cv.put("name", "小明(sqlite)" + System.currentTimeMillis() % 1000);
            cv.put("age", System.currentTimeMillis() % 100);
            db.insert("Person", null, cv);
        }
        querySql();
    }

    private void deleteSql() {
        db.delete("Person", "name like '%sqlite%'", null);
        querySql();
    }

    private void updateSql() {
        ContentValues cv = new ContentValues();
        cv.put("age", System.currentTimeMillis() % 100);
        db.update("Person", cv, "name like '%6%' and name like '%sqlite%'", null);
        querySql();
    }

    private void querySql() {
        Cursor cr = db.rawQuery("select pid,name,age from Person", null);
        int count = refreshLv(cr);
        if(count<=0){
            Toast.makeText(ProviderMySelfActivity.this, "木有数据哦", Toast.LENGTH_SHORT).show();
        }
    }

    //=====================================内容提供者=============================

    private void insertByPro() {
        ContentValues cv = new ContentValues();
        for (int i = 0; i < 10; i++) {
            cv.put("name", "小明(provider)" + System.currentTimeMillis() % 1000);
            cv.put("age", System.currentTimeMillis() % 100);
            provider.insert(Uri.parse(PROVIDER_CONTENT_AUTHORITES + "/person"), cv);
        }
        queryPro();
    }

    private void deletePro() {
        provider.delete(Uri.parse(PROVIDER_CONTENT_AUTHORITES + "/person"), "name like '%provider%'", null);
        queryPro();
    }

    private void updatePro() {
        ContentValues cv = new ContentValues();
        cv.put("age", System.currentTimeMillis() % 100);
        provider.update(Uri.parse(PROVIDER_CONTENT_AUTHORITES + "/person"), cv, "name like '%6%' and name like '%provider%'", null);
        queryPro();
    }

    private void queryPro() {
        Cursor cr = provider.query(Uri.parse(PROVIDER_CONTENT_AUTHORITES + "/person"), null, null, null, null);
        refreshLv(cr);
    }

    private void queryProById() {
        String id = providerMyProEdt.getText().toString().trim();
        if(TextUtils.isEmpty(id)){
            Toast.makeText(ProviderMySelfActivity.this, "请输入id", Toast.LENGTH_SHORT).show();
            return;
        }
        Cursor cr = provider.query(Uri.parse(PROVIDER_CONTENT_AUTHORITES + "/person/"+id),null,null,null,null);
        refreshLv(cr);
    }


    private int refreshLv(Cursor cr) {
        if (cr == null)
            return -1;
        boolean isNext = cr.moveToFirst();
        List<PersonBean> list = new ArrayList<>();
        while (isNext) {
            PersonBean p = new PersonBean();
            p.pid = cr.getInt(cr.getColumnIndex("pid"));
            p.name = cr.getString(cr.getColumnIndex("name"));
            p.age = cr.getInt(cr.getColumnIndex("age"));
            list.add(p);
            Log.i(TAG, "querySql: " + p);
            isNext = cr.moveToNext();
        }
        mAdapter.resetData(list);
        return list.size();
    }
}
