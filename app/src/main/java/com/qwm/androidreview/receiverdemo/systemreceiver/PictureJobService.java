package com.qwm.androidreview.receiverdemo.systemreceiver;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class PictureJobService extends JobService {

    public static final int PICTURE_CODE = 1003;
    private static final String TAG = "PictureJobService";

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            jobFinished((JobParameters)msg.obj,false);
            Toast.makeText(getApplicationContext(),"run----",Toast.LENGTH_LONG).show();
            Log.i(TAG, "handleMessage: -------------");
        }
    };

    @Override
    public boolean onStartJob(JobParameters params) {
        mHandler.sendMessage(Message.obtain(mHandler,PICTURE_CODE,params));
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        mHandler.removeMessages(PICTURE_CODE);
        return false;
    }
}
