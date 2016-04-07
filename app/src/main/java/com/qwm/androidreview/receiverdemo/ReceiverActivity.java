package com.qwm.androidreview.receiverdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qwm.androidreview.R;

public class ReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
    }

    /**
     * 测试自己定义的广播
     * @param view
     */
    public void onReceiverTest(View view){
        Intent intent = new Intent("com.qwm.test");
        intent.putExtra("msg","明哥的自定义广播");
        sendBroadcast(intent);
    }
}
