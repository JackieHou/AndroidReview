package com.qwm.androidreview.servicedemo;

import android.os.Bundle;
import android.view.View;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;

public class ServiceDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);
    }

    public void testSerice(View view){
        startActivity("Serice测试",ServiceActivity.class);
    }

    public void testIntentService(View view){
        startActivity("IntentService测试",IntentServiceActivity.class);
    }

    public void testSericeInter(View view){
        startActivity("Service测试",SeriveInteractiveActivity.class);
    }
}
