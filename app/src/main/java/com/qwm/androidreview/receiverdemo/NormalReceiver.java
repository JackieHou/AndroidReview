package com.qwm.androidreview.receiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/4/5<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class NormalReceiver extends BroadcastReceiver {
    private static final String TAG = "NormalReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String test = intent.getStringExtra("msg");
        Log.i(TAG, "NormalReceiver_onReceive: "+test);
        //开启activity
        Intent aintent =new Intent(context,NormalReceiverActivity.class);
        aintent.putExtra("msg",test);
        context.startActivity(aintent);
    }
}
