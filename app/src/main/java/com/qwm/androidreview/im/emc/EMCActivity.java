package com.qwm.androidreview.im.emc;

import android.os.Bundle;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.im.emc.ui.LoginActivity;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/8/22<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class EMCActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity("登录", LoginActivity.class);
    }
}
