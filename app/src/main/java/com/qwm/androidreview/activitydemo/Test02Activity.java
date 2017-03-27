package com.qwm.androidreview.activitydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;

public class Test02Activity extends BaseActivity {
    
    private String TAG = Test02Activity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        Log.i(TAG, "onCreate------");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy------");
    }
}
