package com.qwm.androidreview.activitydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.qwm.androidreview.R;

public class Test02Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        Log.i("anctivity--", "onCreate------");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("anctivity--", "onStart------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("anctivity--", "onResume------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("anctivity--", "onPause------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("anctivity--", "onStop------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("anctivity--", "onDestroy------");
    }
}
