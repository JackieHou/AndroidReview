package com.qwm.androidreview.fragmentdemo;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.qwm.androidreview.R;

public class FragmentDemoActivity extends AppCompatActivity {

    private String TAG = FragmentDemoActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);

        MyFragmentOne tf1 = new MyFragmentOne();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl_content,tf1,MyFragmentOne.class.getSimpleName());
        ft.commit();

    }








    public void testBtn1(View view) {
        MyFragmentOne tf1 = new MyFragmentOne();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_content,tf1,MyFragmentOne.class.getSimpleName());
        ft.addToBackStack("jj");
        ft.commit();
    }

    public void testBtn2(View view) {
        TestFragment02 tf2 = new TestFragment02();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_content,tf2,TestFragment02.class.getSimpleName());
        ft.addToBackStack(TestFragment02.class.getSimpleName());
        ft.commit();
    }

    public void testBtn3(View view) {
        TestFragment03 tf3 = new TestFragment03();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_content,tf3,TestFragment03.class.getSimpleName());
        ft.commit();
    }

    public void testBtn4(View view) {
        TestFragment04 tf4 = new TestFragment04();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_content,tf4,TestFragment04.class.getSimpleName());
        ft.addToBackStack(TestFragment04.class.getSimpleName());
        ft.commit();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}
