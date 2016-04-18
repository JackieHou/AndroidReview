package com.qwm.androidreview.receiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/** @author qiwenming
 * @date 2016/3/29 11:16
 * @ClassName: TestReceiver
 * @Description: 广播接受者测试
 */
public class TestReceiver extends BroadcastReceiver{

    private String TAG = TestReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals("com.qwm.testxm")){
            String test = intent.getStringExtra("test");

            Log.i(TAG, "------test: "+test);

            Bundle bundle= getResultExtras(false);
            if(bundle!=null){
                String b1 =  bundle.getString("b1");
                String b2 =  bundle.getString("b2");
                String b3 =  bundle.getString("b3");
                Log.i(TAG, "------b1: "+b1);
                Log.i(TAG, "------b2: "+b2);
                Log.i(TAG, "------b3: "+b3);
            }
        }else{
            String msg = intent.getStringExtra("msg");
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        }
    }
}
