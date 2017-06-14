package com.qwm.androidreview.jobschedulerdemo;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.qwm.androidreview.jobschedulerdemo.JobschedulerActivity.MESSENGER_INTENT_KEY;
import static com.qwm.androidreview.jobschedulerdemo.JobschedulerActivity.WORK_DURATION_KEY;
import static com.qwm.androidreview.jobschedulerdemo.JobschedulerActivity.MSG_COLOR_START;
import static com.qwm.androidreview.jobschedulerdemo.JobschedulerActivity.MSG_COLOR_STOP;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/6/13<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b>
 * 我们实现的就是  定时一段时间  开始定时之前发一个消息，结束发一个消息
 * <br>
 */
public class MyJobService extends JobService {
    private static final String TAG = "MyJobService";

    private Messenger mActivityMessenger;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        //获取我们信使
        mActivityMessenger = intent.getParcelableExtra(MESSENGER_INTENT_KEY);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: -----");
    }

    @Override
    public boolean onStartJob(final JobParameters params) {
        Log.i(TAG, "onStartJob: ----------"+params.getJobId());
        long duration = params.getExtras().getLong(WORK_DURATION_KEY);
        sendMessage(MSG_COLOR_START,params);
        //定时一会 定时完成以后，调用我们需要 jobFinished
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sendMessage(MSG_COLOR_STOP,params);
                jobFinished(params,false);
            }
        }, duration);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG, "onStopJob: ----------"+params.getJobId());
        sendMessage(MSG_COLOR_STOP, params.getJobId());
        return false;
    }

    private void sendMessage(int messageID, @Nullable Object params) {
        // If this service is launched by the JobScheduler, there's no callback Messenger. It
        // only exists when the MainActivity calls startService() with the callback in the Intent.
        if (mActivityMessenger == null) {
            Log.d(TAG, "Service is bound, not started. There's no callback to send a message to.");
            return;
        }
        Message m = Message.obtain();
        m.what = messageID;
        m.obj = params;
        try {
            mActivityMessenger.send(m);
        } catch (RemoteException e) {
            Log.e(TAG, "Error passing service object back to activity.");
        }
    }
}
