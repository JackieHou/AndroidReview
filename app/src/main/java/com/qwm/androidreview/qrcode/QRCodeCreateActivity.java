package com.qwm.androidreview.qrcode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.ImageView;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;
import com.qwm.androidreview.utils.ZXingUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QRCodeCreateActivity extends BaseActivity {

    @Bind(R.id.qrcode_iv)
    ImageView mQrcodeIv;
    @Bind(R.id.qrcode_edt)
    AppCompatEditText mQrcodeEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_create);
        ButterKnife.bind(this);
    }

    public void onCreateQRCode(View view) {
        Bitmap bitmap = ZXingUtils.createQRImage(mQrcodeEdt.getText().toString(),mQrcodeIv.getWidth(),mQrcodeIv.getHeight());
        mQrcodeIv.setImageBitmap(bitmap);
    }
}
