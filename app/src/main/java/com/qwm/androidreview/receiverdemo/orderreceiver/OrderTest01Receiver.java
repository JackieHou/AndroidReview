package com.qwm.androidreview.receiverdemo.orderreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/6/15<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class OrderTest01Receiver extends BroadcastReceiver {
    private static final String TAG = "OrderTest01Receiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.i(TAG, "====================OrderTest01Receiver====================");
        Log.i(TAG, "action: "+intent.getAction());
        Log.i(TAG, "test1: "+intent.getStringExtra("test1"));
        Log.i(TAG, "bundel name: "+bundle.getString("name"));
        Log.i(TAG, "bundel age: "+bundle.getInt("age"));
        Log.i(TAG, "===========================================================");

        //修改数据 这个是无法修改的
        intent.putExtra("test1","====================OrderTest01Receiver====================");

        //这个是可以修改的
        bundle.putInt("age",18);
        bundle.putString("name","xiaoming");
        //注意调用的是这个方法
        setResultExtras(bundle);
    }
}
