package com.qwm.androidreview.receiverdemo.localreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.os.Bundle;
import android.view.View;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;
/**
 * <b>Project:</b> androidreview<br>
 * <b>Create Date:</b> 2017/6/15<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b>
 * 本地广播测试
 * <br>
 */
public class LocalReceiverActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_receiver);
        LocalBroadcastManager.getInstance(this).registerReceiver(new Local01Receiver(),new IntentFilter(MyAction.local_test1));
    }

    public void localTest1(View view){
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.sendBroadcast(new Intent(MyAction.local_test1));
    }
}
