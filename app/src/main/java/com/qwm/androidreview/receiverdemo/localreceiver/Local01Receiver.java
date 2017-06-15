package com.qwm.androidreview.receiverdemo.localreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/6/15<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class Local01Receiver extends BroadcastReceiver {
    private static final String TAG = "Local01Receiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: "+intent.getAction());
    }
}
