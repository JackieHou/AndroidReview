package com.qwm.androidreview.receiverdemo.systemreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TakePhotoReceiver extends BroadcastReceiver {
    public TakePhotoReceiver() {
    }

    private static final String TAG = "TakePhotoReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: "+intent.getData());
    }
}
