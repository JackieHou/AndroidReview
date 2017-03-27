package com.qwm.androidreview;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/3/27<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class BaseActivity extends AppCompatActivity {
    public static String ACTIVITY_FROM_TITLE = "FROM_TITLE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String fromTitle = getIntent().getStringExtra(ACTIVITY_FROM_TITLE);
        if(!TextUtils.isEmpty(fromTitle)){
            setTitle(fromTitle);
        }
    }

    public void startActivity(String title,Class activityClass){
        Intent intent = new Intent(this,activityClass);
        intent.putExtra(ACTIVITY_FROM_TITLE,title);
        startActivity(intent);
    }
}
