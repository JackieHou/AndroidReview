package com.qwm.androidreview.activitydemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;

public class Test01Activity extends BaseActivity {

    private String TAG = Test01Activity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_test);
    }

    public void onTest(View view){
//        显示启动
        startActivity(new Intent(this, DialogActivity.class));
    }

    public void onTest2(View view){
        //显示启动
        Intent intent = new Intent();
        //----1----
//        intent.setClass(this,Test02Activity.class);
        //-----2-----
        intent.setClassName(this,"com.qwm.androidreview.activitydemo.Test02Activity");
        //-----3----
//        intent.setClassName("com.qwm.androidreview.activitydemo","com.qwm.androidreview.activitydemo.Test02Activity");
        startActivity(intent);
    }

    public void onTest3(View view){
        startActivity(new Intent("com.qwm.androidreview.activitydemo.test"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

}
