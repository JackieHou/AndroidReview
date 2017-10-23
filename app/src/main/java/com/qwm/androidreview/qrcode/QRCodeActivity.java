package com.qwm.androidreview.qrcode;

import android.os.Bundle;
import android.view.View;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;

public class QRCodeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
    }


    public void onCreateQRCode(View view){
        startActivity("二维码生成测试",QRCodeCreateActivity.class);
    }
}
