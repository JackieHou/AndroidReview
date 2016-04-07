package com.qwm.androidreview.activitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.qwm.androidreview.R;

public class Test01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("anctivity--", "onCreate");
        setContentView(R.layout.activity_test);
    }

    public void onTest(View view){
        startActivity(new Intent(this, DialogActivity.class));
    }

    public void onTest2(View view){
        startActivity(new Intent(this,Test02Activity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("anctivity--","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("anctivity--", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("anctivity--", "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("anctivity--", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("anctivity--", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("anctivity--", "onDestroy");
    }

}
