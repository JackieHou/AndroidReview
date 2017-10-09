package com.qwm.androidreview.im;

import android.os.Bundle;
import android.view.View;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;
import com.qwm.androidreview.im.emc.EMCActivity;
import com.qwm.androidreview.im.emc.ui.LoginActivity;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/8/22<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class IMActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im);
    }

    public void clickEMC(View view){
        startActivity("登录",LoginActivity.class);
    }
}
