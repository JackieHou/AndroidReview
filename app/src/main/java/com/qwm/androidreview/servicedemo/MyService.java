package com.qwm.androidreview.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

/** @author qiwenming
 * @date 2016/3/30 12:01
 * @ClassName: MyService
 * @Description:  Service测试
 */
public class MyService extends Service{
    private String TAG = MyService.class.getName();
    public XmBinder xmBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate:");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        Random random = new Random();
        int age = random.nextInt(100);
        xmBinder = new XmBinder(age,"xm"+age);
        return xmBinder ;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    /** @author qiwenming
     * @date 2016/3/30 15:36
     * @ClassName: XmBinder
     * @Description:  服务使用的值传递
     */
    public class XmBinder extends Binder implements XmInterface {

        private int age;
        private String name;

        public XmBinder() {
        }

        public XmBinder(int age, String name) {
            this.age = age;
            this.name = name;
        }


        public MyService getMyService(){
            return MyService.this;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int getAge() {
            return age;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
