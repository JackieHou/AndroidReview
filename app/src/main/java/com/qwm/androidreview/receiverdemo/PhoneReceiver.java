package com.qwm.androidreview.receiverdemo;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/** @author qiwenming
 * @date 2016/3/29 14:14
 * @ClassName: PhoneReceiver
 * @Description:  电话拦截器
 */
public class PhoneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_NEW_OUTGOING_CALL.equals(intent.getAction()) ){
            //打电话
            String phoneNum = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            Toast.makeText(context,"",Toast.LENGTH_SHORT).show();
        }else{
            //来电
            handlerCall(context,intent);
        }
    }

    /**
     *  state 当前电话的状态
     *  TelephonyManager#CALL_STATE_IDLE 空闲
     *  TelephonyManager#CALL_STATE_RINGING 响铃
     *  TelephonyManager#CALL_STATE_OFFHOOK 接通, 摘机.
     * @param intent
     */
    private void handlerCall(Context context,Intent intent) {
        //获取电话管理器
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE);
        Log.i("phone_status",tm.getCallState()+"");
        switch (tm.getCallState()){
            case TelephonyManager.CALL_STATE_RINGING:
                String phone = intent.getStringExtra("incoming_number");
                Toast.makeText(context, "来电了:"+phone, Toast.LENGTH_SHORT).show();
                break;
           case TelephonyManager.CALL_STATE_OFFHOOK:
                break;
           case TelephonyManager.CALL_STATE_IDLE:
                break;
           default:
               break;

        }
    }
}
