package com.qwm.androidreview.receiverdemo;

import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.security.Provider;
import java.util.Set;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if("android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())){
            handleSms(intent);
        }
    }

    private static final String TAG = "SmsReceiver";
    
    public void handleSms(Intent intent){
        //取出短息内容
        Bundle bundle = intent.getExtras();
//        String[] keyArr= bundle.keySet().toArray(new String[]{});
//        for (String key:keyArr) {
//            Log.i(TAG, "handleSms:  --->"+key+":"+bundle.get(key));
//        }
        Object[] pdus = (Object[])bundle.get("pdus");
        for (Object object:pdus) {
            //每一条短信都是一个byte数组
            SmsMessage sms = SmsMessage.createFromPdu((byte[])object);
            printSms(sms);
        }
    }

    public void printSms(SmsMessage sms){
        Log.i(TAG, "================================================");
        Log.i(TAG, "OriginatingAddress: "+sms.getOriginatingAddress());
        Log.i(TAG, "DisplayOriginatingAddress: "+sms.getDisplayOriginatingAddress());
        Log.i(TAG, "ServiceCenterAddress: "+sms.getServiceCenterAddress());
        Log.i(TAG, "MessageBody: "+sms.getMessageBody());
        Log.i(TAG, "DisplayMessageBody: "+sms.getDisplayMessageBody());
        Log.i(TAG, "UserData: "+new String(sms.getUserData()));
        Log.i(TAG, "OriginatingAddress: "+sms.getOriginatingAddress());

    }
}
