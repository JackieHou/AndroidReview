package com.qwm.androidreview;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.qwm.androidreview.DesignDemo.DesignSuportDemoActivity;
import com.qwm.androidreview.activitydemo.Test01Activity;
import com.qwm.androidreview.activityservicedemo.ActivityServiceActivity;
import com.qwm.androidreview.adapter.MyGridAdapter;
import com.qwm.androidreview.animodemo.AnimoActivity;
import com.qwm.androidreview.asyncdemo.AsyncActivity;
import com.qwm.androidreview.bluetoothdemo.BluetoothDemoActivity;
import com.qwm.androidreview.eventdemo.EventActivity;
import com.qwm.androidreview.filestoragedemo.FileStorageActivity;
import com.qwm.androidreview.fragmentdemo.FragmentDemoActivity;
import com.qwm.androidreview.listazdemo.ListViewAZDemoctivity;
import com.qwm.androidreview.listviewdemo.ListViewActivity;
import com.qwm.androidreview.materialdesigndemo.MDDemoActivity;
import com.qwm.androidreview.picassodemo.PicassoDemoActivity;
import com.qwm.androidreview.pictureoomdemo.PictureOOMActivity;
import com.qwm.androidreview.providerdemo.ProviderActivity;
import com.qwm.androidreview.receiverdemo.ReceiverActivity;
import com.qwm.androidreview.screendemo.ScreenActivity;
import com.qwm.androidreview.servicedemo.ServiceActivity;
import com.qwm.androidreview.shapedemo.ShapeDemoActivity;
import com.qwm.androidreview.view.MyGridView;
import com.qwm.androidreview.viewdemo.ViewActivity;
import com.qwm.androidreview.viewpagerdemo.ViewpagerActivity;
import com.qwm.androidreview.vollydemo.VolleyActivity;
import com.qwm.androidreview.xmljsondemo.XmlJsonActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyGridView contentMgv;
    private ArrayList<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGridView();
    }


    public void initGridView() {
        contentMgv = (MyGridView) findViewById(R.id.mgv_content);
        itemList = new ArrayList<String>();
        itemList.add("Activity测试");
        itemList.add("广播接受者测试");
        itemList.add("内容提供者测试");
        itemList.add("Service测试");
        itemList.add("Service和Activity");
        itemList.add("ListView测试");
        itemList.add("图片OOM测试");
        itemList.add("动画测试");
        itemList.add("Shape测试");
        itemList.add("屏幕适配");
        itemList.add("view");
        itemList.add("数据存储");
        itemList.add("Xml和Json");
        itemList.add("异步测试");
        itemList.add("事件分发");
        itemList.add("Volley");
        itemList.add("Fragment");
        itemList.add("ListView A-Z");
        itemList.add("Viewpager");
        itemList.add("Picasso测试");
        itemList.add("Bluetooth测试");
        itemList.add("Design Support Library测试");
        itemList.add("Material Design");
        contentMgv.setAdapter(new MyGridAdapter(this, itemList));
        contentMgv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemStr = itemList.get(position);
                if ("Activity测试".equals(itemStr)) {
                    onTest(null);
                } else if ("广播接受者测试".equals(itemStr)) {
                    onTestReceiver(null);
                } else if ("内容提供者测试".equals(itemStr)) {
                    onTestProvider(null);
                } else if ("Service测试".equals(itemStr)) {
                    onTestService();
                }else if ("Service和Activity".equals(itemStr)) {
                    startActivity(new Intent(MainActivity.this, ActivityServiceActivity.class));
                } else if ("ListView测试".equals(itemStr)) {
                    startActivity(new Intent(MainActivity.this, ListViewActivity.class));
                } else if ("图片OOM测试".equals(itemStr)) {
                    startActivity(new Intent(MainActivity.this, PictureOOMActivity.class));
                } else if ("动画测试".equals(itemStr)) {
                    startActivity(new Intent(MainActivity.this, AnimoActivity.class));
                } else if ("Shape测试".equals(itemStr)) {
                    startActivity(new Intent(MainActivity.this, ShapeDemoActivity.class));
                } else if ("屏幕适配".equals(itemStr)) {
                    startActivity(new Intent(MainActivity.this, ScreenActivity.class));
                } else if ("view".equals(itemStr)) {
                    startActivity(new Intent(MainActivity.this, ViewActivity.class));
                } else if ("数据存储".equals(itemStr)) {
                    startActivity(new Intent(MainActivity.this, FileStorageActivity.class));
                }else if ("Xml和Json".equals(itemStr)) {
                    startActivity(new Intent(MainActivity.this, XmlJsonActivity.class));
                }else if ("异步测试".equals(itemStr)) {
                    startActivity(new Intent(MainActivity.this, AsyncActivity.class));
                }else if ("事件分发".equals(itemStr)) {
                    startActivity(new Intent(MainActivity.this, EventActivity.class));
                }else if ("Volley".equals(itemStr)) {
                    startActivity(new Intent(MainActivity.this, VolleyActivity.class));
                }else if ("Fragment".equals(itemStr)) {
                    startActivity(new Intent(MainActivity.this, FragmentDemoActivity.class));
                }else if ("ListView A-Z".equals(itemStr)) {
                    startActivity(new Intent(MainActivity.this, ListViewAZDemoctivity.class));
                }else if ("Viewpager".equals(itemStr)) {
                    startActivity(new Intent(MainActivity.this, ViewpagerActivity.class));
                }else if ("Picasso测试".equals(itemStr)) {
                    startActivity(new Intent(MainActivity.this, PicassoDemoActivity.class));
                }else if ("Bluetooth测试".equals(itemStr)) {
                    startActivity(new Intent(MainActivity.this, BluetoothDemoActivity.class));
                }else if ("Design Support Library测试".equals(itemStr)) {
                    startActivity(new Intent(MainActivity.this, DesignSuportDemoActivity.class));
                }else if ("Material Design".equals(itemStr)) {
                    startActivity(new Intent(MainActivity.this, MDDemoActivity.class));
                }
            }
        });

    }


    //====================================================

    /**
     * 广播接收者测试
     *
     * @param view
     */
    public void onTestReceiver(View view) {
//        getAppSatus(this,"com.qwm.androidreview");
        startActivity(new Intent(this, ReceiverActivity.class));
    }

    /**
     * 内容提供者
     *
     * @param view
     */
    public void onTestProvider(View view) {
//        getAppSatus(this,"com.qwm.androidreview");
        startActivity(new Intent(this, ProviderActivity.class));
    }

    /**
     * Service测试
     */
    public void onTestService(){
        startActivity(new Intent(this, ServiceActivity.class));
    }

    //=============================前后台状态判断=================================
    public void onTest(View view) {
//        getAppSatus(this,"com.qwm.androidreview");
        startActivity(new Intent(this, Test01Activity.class));
    }


    /**
     * 返回app运行状态
     * 1:程序在前台运行
     * 2:程序在后台运行
     * 3:程序未启动
     * 注意：需要配置权限<uses-permission android:name="android.permission.GET_TASKS" />
     */
    public int getAppSatus(Context context, String pageName) {

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(20);

        //判断程序是否在栈顶
        if (list.get(0).topActivity.getPackageName().equals(pageName)) {
            return 1;
        } else {
            //判断程序是否在栈里
            for (ActivityManager.RunningTaskInfo info : list) {
                if (info.topActivity.getPackageName().equals(pageName)) {
                    return 2;
                }
            }
            return 3;//栈里找不到，返回3
        }
    }

}
