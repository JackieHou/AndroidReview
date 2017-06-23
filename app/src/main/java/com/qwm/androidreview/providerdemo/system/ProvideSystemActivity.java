package com.qwm.androidreview.providerdemo.system;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
public class ProvideSystemActivity extends BaseActivity {
    private static final String TAG = "ProvideSystemActivity";
    @Bind(R.id.provider_sys_gv)
    GridView providerSysGv;
    @Bind(R.id.provider_sys_lv)
    ListView providerSysLv;

    private List<String> mButtonTexts = Arrays.asList(
            "插入短信","查看短信","插入联系人","删除联系人","修改联系人","查询联系人"
    );

    private SmsAdapter mSmsAdapter = null;
    private ContactsAdapter mConAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provide_system);
        ButterKnife.bind(this);
        mSmsAdapter = new SmsAdapter(this,null);
        mConAdapter = new ContactsAdapter(this,null);
        initGv();
    }
    private void initGv() {
        ButtonAdapter buttonAdapter = new ButtonAdapter(this,mButtonTexts);
        providerSysGv.setAdapter(buttonAdapter);
        providerSysGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemStr = mButtonTexts.get(position);
                if("插入短信".equals(itemStr)){
                    insertSms();
                }else  if("查看短信".equals(itemStr)){
                    querySms();
                }else if("插入联系人".equals(itemStr)){
                    insertCon();
                }else if("删除联系人".equals(itemStr)){
                    deleteCon();
                }else if("修改联系人".equals(itemStr)){
                    updateCon();
                }else if("查询联系人".equals(itemStr)){
                    queryCon();
                }
            }
        });
    }

    private void insertSms() {
        ContentResolver cr = getContentResolver();
        ContentValues[] cvs = new ContentValues[20];
        for (int i=80;i<100;i++){
            ContentValues cv = new ContentValues();
            cv.put("address","130266297"+i);
            cv.put("body","我是小明，我最帅"+i);
            cv.put("date", System.currentTimeMillis());
            cv.put("type", i%2+1);
            cvs[i-80] = cv;
//            cr.insert(Uri.parse("content://sms"),cv);
        }
        cr.bulkInsert(Uri.parse("content://sms"),cvs);
    }

    private void querySms() {
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(Uri.parse("content://sms"), new String[]{"address","body","date","type"}, null, null, null);
        List<SmsBean> smsBeanList = new ArrayList<>();
        while(cur.moveToNext()){
            String address = cur.getString(cur.getColumnIndex("address"));
            String body = cur.getString(cur.getColumnIndex("body"));
            long date = cur.getLong(cur.getColumnIndex("date"));
            int type = cur.getInt(cur.getColumnIndex("type"));
            SmsBean sms = new SmsBean(address,body,date,type);
            smsBeanList.add(sms);
            Log.i(TAG, "querySms: "+sms);
        }
        mSmsAdapter.resetData(smsBeanList);
        providerSysLv.setAdapter(mSmsAdapter);
    }

    private void insertCon() {
        ContentResolver cr = getContentResolver();
        //获取最大的联系人id
        Cursor cur = cr.query(Uri.parse("content://com.android.contacts/raw_contacts"), new String[]{"_id"}, null, null, null);
        int id = 1;
        if(cur.moveToLast()){
            id = cur.getInt(0)+1;
        }
        //选好设置id
        List<Integer> ids = new ArrayList<>();
        ContentValues[] cvs = new ContentValues[20];
        for (int i = 0; i < 20; i++) {
            ContentValues cv = new ContentValues();
            cv.put("_id", id+i );
            cvs[i] = cv;
            ids.add(id+i);
        }
        //把id插入数据库
        cr.bulkInsert(Uri.parse("content://com.android.contacts/raw_contacts"),cvs);

        //下面是数据的插入
        ContentValues[] ncvs = new ContentValues[20];
        ContentValues[] pcvs = new ContentValues[20];
        ContentValues[] ecvs = new ContentValues[20];
        for (int i = 0; i < ids.size(); i++) {
            int _id = ids.get(i);
            ContentValues cv = new ContentValues();
            cv.put("raw_contact_id", _id);
            cv.put("data1", "小明"+_id);
            cv.put("mimetype", "vnd.android.cursor.item/name");
            ncvs[i] = cv;

            cv = new ContentValues();
            cv.put("raw_contact_id", _id);
            cv.put("data1", "187885403"+(10+i));
            cv.put("mimetype", "vnd.android.cursor.item/phone_v2");
            pcvs[i] = cv;

            cv = new ContentValues();
            cv.put("raw_contact_id", _id);
            cv.put("data1", _id +"@qq.com");
            cv.put("mimetype", "vnd.android.cursor.item/email_v2");
            ecvs[i] = cv;
        }

        cr.bulkInsert(Uri.parse("content://com.android.contacts/data"), ncvs);
        cr.bulkInsert(Uri.parse("content://com.android.contacts/data"), pcvs);
        cr.bulkInsert(Uri.parse("content://com.android.contacts/data"), ecvs);
    }

    private void deleteCon() {
        ContentResolver cr =getContentResolver();
        cr.delete(Uri.parse("content://com.android.contacts/raw_contacts"),null,null);
        cr.delete(Uri.parse("content://com.android.contacts/data"),null,null);
        Toast.makeText(ProvideSystemActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
        queryCon();
    }

    private void updateCon() {

    }

    private void queryCon() {
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(Uri.parse("content://com.android.contacts/raw_contacts"),
                new String[]{"contact_id"}, null, null, null);
        List<ContactsBean> list = new ArrayList<>();
        while(cur.moveToNext()){

            String contactontid = cur.getString(0);
//            Contact c1 = new Contact();
            Cursor cur2 = cr.query(Uri.parse("content://com.android.contacts/data"),
                    new String[]{"data1","mimetype"},"raw_contact_id=?", new String[]{contactontid}, null);
            ContactsBean c1 = new ContactsBean();
            while(cur2.moveToNext()){
                String data1 = cur2.getString(cur2.getColumnIndex("data1"));
                String mimetype = cur2.getString(cur2.getColumnIndex("mimetype"));
                if("vnd.android.cursor.item/email_v2".equals(mimetype)){
                    c1.email = data1;
                }else if("vnd.android.cursor.item/name".equals(mimetype)){
                    c1.name = data1;
                } else if("vnd.android.cursor.item/phone_v2".equals(mimetype)){
                    c1.phone = data1;
                }
                Log.i(TAG, "queryCon: "+data1+"---"+mimetype);
            }
//            Log.i(TAG, "queryCon: "+c1);
            list.add(c1);
        }
        mConAdapter.resetData(list);
        providerSysLv.setAdapter(mConAdapter);
        Toast.makeText(this, "查询联系人成功", Toast.LENGTH_SHORT).show();
    }
}
