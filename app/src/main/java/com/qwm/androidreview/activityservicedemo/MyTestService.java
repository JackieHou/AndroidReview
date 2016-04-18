package com.qwm.androidreview.activityservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
/**
 * @author qiwenming
 * @date 2016/4/17 21:43
 * @ClassName: MyTestService
 * @Description:  模拟下载，  使用广播和activity通信
 */
public class MyTestService extends Service {
    private String TAG = MyTestService.class.getName();
    private Intent intent = null;
    private int progress = 0;
    public static final int MAXPROGRESS = 10;
    public static final  String action_broad = "com.qwm.servertest1";
    public MyTestService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        intent = new Intent(action_broad);
        //打开下载
        new MyTask().start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    /**
     * 模拟下载，1秒发一个广播
     */
    public class MyTask extends Thread{
        @Override
        public void run() {
            while (progress<=MAXPROGRESS){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                intent.putExtra("progress",progress);
                sendBroadcast(intent);
                progress++;
            }
            stopSelf();
        }
    }

}
