package com.qwm.androidreview.providerdemo;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;
import com.qwm.androidreview.sqldemo.MyOpenHelper;

import java.util.List;

/** @author qiwenming
 * @date 2016/3/29 16:46
 * @ClassName: ProviderActivity
 * @Description:  内容提供者的Activity
 */
public class ProviderActivity extends BaseActivity {

    private String TAG = ProviderActivity.class.getName();

    private TextView smsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        smsTv = (TextView) findViewById(R.id.tv_sms);
    }

    /** @author qiwenming
     * @date 2016/3/29 17:13
     * @ClassName: ProviderActivity
     * @Description:  插入短信
     */
    public void insertSms(View view){
        ContentResolver cr = getContentResolver();
        ContentValues cv = new ContentValues();
        for (int i = 80; i < 100; i++) {
            cv.put("address","130266297"+i);
            cv.put("body","我是小明，我最帅"+i);
            cv.put("date", System.currentTimeMillis());
            cv.put("type", i%2+1);
            cr.insert(Uri.parse("content://sms"), cv);
        }
    }

    /**
     * 获取短息
     * @param view
     */
    public void getSms(View view){
        ContentResolver cr = getContentResolver();
        //关键是下面这句话
        Cursor cur = cr.query(Uri.parse("content://sms"), new String[]{"address","body","date","type"}, null, null, null);
        StringBuilder sb = new StringBuilder();
        while(cur.moveToNext()){
            String address = cur.getString(cur.getColumnIndex("address"));
            String body = cur.getString(cur.getColumnIndex("body"));
            String date = cur.getString(cur.getColumnIndex("date"));
            String type = cur.getString(cur.getColumnIndex("type"));
            type = "1".equals(type)?"发送":"接受";
            sb.append("\n----------------------");
            sb.append("\r\n电话："+address);
            sb.append("\n短信："+type);
            sb.append("\n时间："+date);
            sb.append("\n内容："+body);
        }
        Log.d(TAG, "getSms: "+sb.toString());
        smsTv.setText(sb.toString());
    }
}
