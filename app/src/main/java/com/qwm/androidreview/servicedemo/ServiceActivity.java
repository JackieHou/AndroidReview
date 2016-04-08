package com.qwm.androidreview.servicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.qwm.androidreview.R;

public class ServiceActivity extends AppCompatActivity {

    private Intent intent = null;
    private ServiceConnection sc;
    private MyService.XmBinder binder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    /**
     * 开启服务
     * @param view
     */
    public void testStartService(View view){
        if(null==intent){
            intent = new Intent(this,MyService.class);
        }
        startService(intent);
    }

    /**
     * 停止服务
     * @param view
     */
    public void onStopService(View view){
        stopService(intent);
    }

    /**
     * 绑定服务
     * @param view
     */
    public void testBindService(View view){
        if(null==intent){
            intent = new Intent(this,MyService.class);
        }
        sc = new MyServiceConnection();
        bindService(intent, sc, Context.BIND_AUTO_CREATE);
    }

    /**
     * 解绑服务
     * @param view
     */
    public void testUnBindService(View view){
        if(sc==null)
            return;
        unbindService(sc);
    }

    /**
     * 获取值
     * @param view
     */
    public void getData(View view){
        if(binder==null)
            return;
        Toast.makeText(this,binder.getName()+"--"+binder.getName(),Toast.LENGTH_SHORT).show();
    }

    class MyServiceConnection implements  ServiceConnection{



        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
           binder = (MyService.XmBinder)service;
        }


        @Override
        public void onServiceDisconnected(ComponentName name) {
            binder = null;
        }
    }
}
