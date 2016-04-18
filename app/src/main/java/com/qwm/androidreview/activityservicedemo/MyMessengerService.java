package com.qwm.androidreview.activityservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 * @author qiwenming
 * @date 2016/4/17 23:01
 * @ClassName: MyMessengerService
 * @Description:  使用 Messenger （信使的方式）
 * 原理：
 *   Messenger翻译过来指的是信使，它引用了一个Handler对象，
 *   别人能够向它发送消息(使用mMessenger.send(Message msg)方法)。
 *   该类允许跨进程间基于Message通信，在服务端使用Handler创建一个 Messenger，
 *   客户端只要获得这个服务端的Messenger对象就可以与服务端通信了。
 *   也就是说我们可以把Messenger当做Client端与Server端的传话筒，这样就可以沟通交流了
 *
 *
 *   使用Messenger作中间桥梁
 */
public class MyMessengerService extends Service {
    private String TAG = MyMessengerService.class.getName();

    private int progress = 0;

    public static final int MAXPROGRESS = 10;

    public static final int WHTAT_MESSENGAR = 110000;

    private Messenger serviceMessenger = null;

    private Messenger clientMessenger = null;



    public MyMessengerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        serviceMessenger = new Messenger(mHandler);
    }

    @Override
    public IBinder onBind(Intent intent) {
      return  serviceMessenger.getBinder();
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

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //客户端的数据
            clientMessenger = msg.replyTo;
            if(msg.getData().getBoolean("isStart")){//可以开始下载了
                new MyTask().start();
            }
        }
    };


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
                //给客户端发送信息 进度
                Bundle bundle = new Bundle();
                bundle.putInt("progress",progress);
                Message msg = new Message();
                msg.what = WHTAT_MESSENGAR;
                msg.setData(bundle);
                try {
                    clientMessenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                progress++;
            }
            stopSelf();
        }
    }
}
