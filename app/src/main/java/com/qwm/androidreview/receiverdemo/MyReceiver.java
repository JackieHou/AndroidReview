package com.qwm.androidreview.receiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    private String TAG = MyReceiver.class.getName();

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String test = intent.getStringExtra("test");

        Log.i(TAG, "------test: "+test);

        Bundle bundle= intent.getExtras();
        if(bundle!=null){
            String b1 =  bundle.getString("b1");
            String b2 =  bundle.getString("b2");
            String b3 =  bundle.getString("b3");
            Log.i(TAG, "------b1: "+b1);
            Log.i(TAG, "------b2: "+b2);
            Log.i(TAG, "------b3: "+b3);

            bundle.putString("b1",System.currentTimeMillis()+"");

            setResultExtras(bundle);

        }
    }
}
