package com.qwm.androidreview.receiverdemo.systemreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TakeVideoReceiver extends BroadcastReceiver {
    public TakeVideoReceiver() {
    }

    private static final String TAG = "TakeVideoReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: "+intent.getData());
    }
}

