package com.qwm.androidreview.receiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/** @author qiwenming
 * @date 2016/3/29 11:16
 * @ClassName: TestReceiver
 * @Description: 广播接受者测试
 */
public class TestReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
