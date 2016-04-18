package com.qwm.androidreview.activityservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * @author qiwenming
 * @date 2016/4/18 10:54
 * @ClassName: MyInterfaceService
 * @Description:  接口方式的定义
 */
public class MyInterfaceService extends Service {

    /**
     * 进度
     */
    private int progress = 0;
    public  static int MAXPROGRESS = 10;
    private final String TAG = MyInterfaceService.class.getName();

    public MyInterfaceService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        new MyTask().start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return  new MyBinder();
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
     * @author qiwenming
     * @date 2016/4/18 10:58
     * @ClassName: MyInterfaceService
     * @Description:  内部的接口的实现类
     */
    class MyBinder extends Binder implements MyInterface{
        @Override
        public int getProcess() {
            return progress;
        }
    }

    /**
     * @author qiwenming
     * @date 2016/4/18 11:00
     * @ClassName: MyInterfaceService
     * @Description:  任务
     */
    class MyTask extends   Thread{
        @Override
        public void run() {
            while (progress<=MAXPROGRESS){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progress++;
            }
            stopSelf();
        }
    }
}
