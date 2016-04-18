package com.qwm.androidreview.activityservicedemo;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;
/**
 * @author qiwenming
 * @date 2016/4/17 22:26
 * @ClassName: MyShareService
 * @Description:  把数据写入到文件中
 */
public class MyShareService extends Service {

    private String TAG = MyShareService.class.getName();

    private int progress = 0;

    public static final int MAXPROGRESS = 10;

    public static final  String sp_name = "CurrentLoading_SharedPs";

    private SharedPreferences preferences;

    public MyShareService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        preferences = getSharedPreferences(sp_name, MODE_PRIVATE);
        //打开下载
        new MyTask().start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return null;
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
                //写入数据
                preferences.edit().putInt("progress",progress).commit();
                progress++;
            }
            stopSelf();
        }
    }
}
