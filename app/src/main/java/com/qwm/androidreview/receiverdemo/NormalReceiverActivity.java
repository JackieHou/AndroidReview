package com.qwm.androidreview.receiverdemo;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;
/**
 * <b>Project:</b> androidreview<br>
 * <b>Create Date:</b> 2017/6/13<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> 普通广播测试 <br>
 */
public class NormalReceiverActivity extends BaseActivity {

    private TextView tv;
    private BroadcastReceiver mReceiver = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_receiver);
        tv = (TextView)findViewById(R.id.receiver_tv);
        //注册广播
        mReceiver = new NormalReceiver();
        IntentFilter filter = new IntentFilter(MyAction.MY_ACTION_NORMAL);
        registerReceiver(mReceiver,filter);
    }

    public void onReceiverTest1(View view){
        Intent intent = new Intent(MyAction.MY_ACTION_NORMAL);
        intent.putExtra("msg","明哥的自定义广播1_MY_ACTION_NORMAL_"+System.currentTimeMillis());
        sendBroadcast(intent);
    }

    public void onReceiverTest2(View view){
        Intent intent = new Intent(MyAction.MY_ACTION_TEST);
        intent.putExtra("msg","明哥的自定义广播2_MY_ACTION_NORMAL_"+System.currentTimeMillis());
        sendBroadcast(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String text = intent.getStringExtra("msg");
        tv.setText(tv.getText().toString()+"\n"+text);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
