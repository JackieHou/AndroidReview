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
public class OrderTest02Receiver extends BroadcastReceiver {
    private static final String TAG = "OrderTest02Receiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        //intent.getExtras();//这个方法取出来的中途是无法修改的
        Bundle bundle = getResultExtras(false);//通过这个方法
        Log.i(TAG, "====================OrderTest02Receiver====================");
        Log.i(TAG, "action: "+intent.getAction());
        Log.i(TAG, "test1: "+intent.getStringExtra("test1"));
        Log.i(TAG, "bundel name: "+bundle.getString("name"));
        Log.i(TAG, "bundel age: "+bundle.getInt("age"));
        Log.i(TAG, "===========================================================");

        //修改数据 这个是无法修改的
        intent.putExtra("test1","====================OrderTest01Receiver====================");

        //这个是可以修改的
        bundle.putInt("age",50);
        bundle.putString("name","laoming");
        //注意调用的是这个方法
        setResultExtras(bundle);
    }
}
