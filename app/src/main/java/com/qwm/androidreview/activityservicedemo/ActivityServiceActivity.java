package com.qwm.androidreview.activityservicedemo;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;
import com.qwm.androidreview.servicedemo.MyAIDL;

/**
 * @author qiwenming
 * @date 2016/4/17 21:55
 * @ClassName: ActivityServiceActivity
 * @Description:  activity和servic通信测试
 */
public class ActivityServiceActivity extends BaseActivity {

    private String TAG = ActivityServiceActivity.class.getName();
    /**
     * 用于标记现在是那种方式测试
     */
    private String str = "";
    private TextView textTv;

    private MyBroadcastReciever  reciever ;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        textTv = (TextView)findViewById(R.id.tv_text);
        reciever = new MyBroadcastReciever();
        IntentFilter filter = new IntentFilter(MyTestService.action_broad);
        registerReceiver(reciever,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(reciever);
        if(conn!=null){
            unbindService(conn);
        }
        if(interfaceConn!=null){
            unbindService(interfaceConn);
        }
        if(aidlConn!=null){
            unbindService(aidlConn);
        }
    }




    //=================================广播方式测试==========================================
    /**
     * 广播方式测试
     * 实现原理
     *    Service通过广播把结果发送给Activity
     * @param view
     */
    public void testBroadcast(View view){
        str = "广播方式测试";
        Intent intent = new Intent(this,MyTestService.class);
        startService(intent);
    }


    /**
     * @author qiwenming
     * @date 2016/4/17 21:58
     * @ClassName: MyBroadcastReciever
     * @Description:  广播接收者
     */
    class MyBroadcastReciever extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            int progress = intent.getIntExtra("progress",0);
            Log.i(TAG, "onReceive:progress  "+progress);
            textTv.setText(str+":  "+progress);
        }
    }

    //=================================共享文件方式==========================================
    /**
     * 共享文件方式
     * 实现原理
     *    Service把结果写到文件中，
     *    Activity不断的读取文件
     * @param view
     */
    public void testShare(View view){
        Intent intent = new Intent(this,MyShareService.class);
        startService(intent);
        str = "共享文件方式";
        preferences = getSharedPreferences(MyShareService.sp_name, MODE_PRIVATE);
        mHandler.sendEmptyMessageDelayed(FLAG_SHAREFILE,500);
    }

    private final  int FLAG_SHAREFILE = 10001;

    /**
     * handler
     */
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            Log.i(TAG, "handleMessage: ");
            switch (msg.what){
                case FLAG_SHAREFILE://共享的方式
                    handleShare();
                    break;
                case MyMessengerService.WHTAT_MESSENGAR://Messenger方式
                    handleMessenger(msg);
                    break;
                case FLAG_INTERFACE://接口方式处理方式
                    handlerInterce();
                    break;
                case FLAG_AIDL://AIDL方式处理方式
                    handlerAIDL();
                    break;
            }
        }
    };



    /**
     * 共享文件的处理
     */
    public void handleShare(){
        int progress = preferences.getInt("progress",0);
        Log.i(TAG, "handleShare: progress "+progress);
        textTv.setText(str+":  "+progress);
        if(progress<MyShareService.MAXPROGRESS){
            mHandler.sendEmptyMessageDelayed(FLAG_SHAREFILE,500);
        }
        //把值只为0 不然下次没法读取
        preferences.edit().putInt("progress",0).commit();
    }



    //=================================Messengar方式==========================================
    private MyServiceConnection  conn = null;
    private Messenger serverMessenger = null;
    private Messenger clientMessenger = null;

    /**
     * Messengar方式
     *  原理：
     *    服务端创建一个 Messenger对象，
     *    客户端获取到这个对象
     *    通过这个对象通信
     * @param view
     */
    public void testMessenger(View view){
        str = "共享文件方式";
        conn = new MyServiceConnection();
        Intent intent = new Intent(this,MyMessengerService.class);
        bindService(intent,conn,BIND_AUTO_CREATE);
    }

    /**
     * @author qiwenming
     * @date 2016/4/17 23:27
     * @ClassName: ActivityServiceActivity
     * @Description:  连接桥
     */
    class MyServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取服务端的 信使
            serverMessenger = new Messenger(service);
            //客户端信使
            clientMessenger = new Messenger(mHandler);
            Log.i(TAG, "onServiceConnected: ");
            handlerConnect();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            serverMessenger = null;
        }
    }

    private void handlerConnect() {
        Toast.makeText(ActivityServiceActivity.this, "准备就绪，开始---", Toast.LENGTH_SHORT).show();
        try {
            Message msg = new Message();
            //告诉服务端可以下载了
            Bundle cbundle = new Bundle();
            cbundle.putBoolean("isStart",true);
            msg.setData(cbundle);
            //服务端拿到客户端的这个对象，进行通信
            msg.replyTo = clientMessenger;
            serverMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理
     * @param msg
     */
    private void handleMessenger(Message msg) {
//        Log.i(TAG, "handleMessenger: ");
        Bundle bundle = msg.getData();
        int progress = bundle.getInt("progress", 0);
        Log.i(TAG, "handleMessenger:progress  " + progress);
        textTv.setText(str + ":  " + progress);
    }


    //=================================自定义接口方式==========================================
    private MyInterface myInterface;
    private final int FLAG_INTERFACE = 12000;
    private MyInterfaceServiceConnection interfaceConn=null;
    /**
     * 自定义接口方式
     *  原理：
     *    创建一个接口
     *    服务端创建一个这个接口的实现类，并返回实现类的对象，
     *    客户端获取到这个对象
     *    通过这个对象获取值
     * @param view
     */
    public void testInterface(View view){
        str = "自定义接口方式";
        interfaceConn = new MyInterfaceServiceConnection();
        Intent intent = new Intent("com.qwm.servictestinterface");
        bindService(intent,interfaceConn,BIND_AUTO_CREATE);
    }
    /**
     * @author qiwenming
     * @date 2016/4/17 23:27
     * @ClassName: MyInterfaceServiceConnection
     * @Description:  连接桥(接口方式)
     */
    class MyInterfaceServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取服务端的 MyIntterface对象
            myInterface = (MyInterface) service;
            //连接上以后，我们开始更新下载
            mHandler.sendEmptyMessage(FLAG_INTERFACE);
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            serverMessenger = null;
        }
    }

    /**
     * 接口方式的处理
     */
    public void handlerInterce(){
        int progress = myInterface.getProcess();
        Log.i(TAG, "handleMessenger:progress  " + progress);
        textTv.setText(str + ":  " + progress);
        if(progress<MyInterfaceService.MAXPROGRESS){
            mHandler.sendEmptyMessageDelayed(FLAG_INTERFACE,500);
        }
    }

    //=================================AIDL方式==========================================

    private MyAIDLServiceConnection aidlConn  = null;
    private MyAIDL myAidl;
    private final int FLAG_AIDL = 13003;

    /**
    * AIDL方式
    *  原理：
    * @param view
    */
    public void testAIDL(View view){
        str = "AIDL方式";
        Intent intent = new Intent("com.qwm.servictestaidl");
        aidlConn = new MyAIDLServiceConnection();
        bindService(intent,aidlConn,BIND_AUTO_CREATE);
    }

    /**
     * @author qiwenming
     * @date 2016/4/18 11:59
     * @ClassName: ActivityServiceActivity
     * @Description:  连接桥(AIDL)
     */
    class MyAIDLServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取服务端对象
            myAidl = MyAIDL.Stub.asInterface(service);
            //刷新下载
            mHandler.sendEmptyMessage(FLAG_AIDL);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    /**
     * @author qiwenming
     * @date 2016/4/18 12:05
     * @ClassName: ActivityServiceActivity
     * @Description:  AIDL处理方式
     */
    public void handlerAIDL(){
        try {
            int progress = myAidl.getProcess();
            Log.i(TAG, "handlerAIDL:progress  " + progress);
            textTv.setText(str + ":  " + progress);
            if(progress < 10){
                mHandler.sendEmptyMessageDelayed(FLAG_AIDL,500);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
