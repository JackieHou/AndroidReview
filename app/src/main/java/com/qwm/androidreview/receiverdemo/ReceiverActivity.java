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

    public void onReceiverTest2(View view){
        Intent intent = new Intent("com.qwm.testxm");
        intent.putExtra("test","xm--test--");
        Bundle bundle = new Bundle();
        bundle.putString("b1","qwmb1");
        bundle.putString("b2","qwmb2");
        bundle.putString("b3","qwmb3");
        intent.putExtras(bundle);
        sendOrderedBroadcast(intent,null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
