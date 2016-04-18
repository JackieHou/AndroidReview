package com.qwm.androidreview.activityservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.qwm.androidreview.servicedemo.MyAIDL;

/**
 * @author qiwenming
 * @date 2016/4/18 11:49
 * @ClassName: MyAIDLService
 * @Description:  AIDL服务，模拟另外一个apk, IPC
 */
public class MyAIDLService extends Service {

    /**
     * 进度
     */
    private int progress = 0;
    public  static int MAXPROGRESS = 10;
    private final String TAG = MyAIDLService.class.getName();

    public MyAIDLService() {
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
        return new MyAIDLBinder();
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
     * @date 2016/4/18 11:52
     * @ClassName: MyAIDLService
     * @Description:  创建一个 Stub的子类  Stub继承Binder,实现了 MyAIDL
     */
    class MyAIDLBinder extends MyAIDL.Stub{

        @Override
        public int getProcess() throws RemoteException {
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
