package com.qwm.androidreview.windowlearn;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.qwm.androidreview.R;

public class WindowLearnActivity extends AppCompatActivity {
    private static final String TAG = "WindowLearnActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_learn);
        Log.i(TAG, "onCreate: getWindow:"+getWindow());
        Log.i(TAG, "onCreate: "+getWindow().getDecorView());
        printView((ViewGroup) getWindow().getDecorView(),0);
    }


    public void printView(ViewGroup vg,int level){
//        Log.i(TAG, "printView: "+getSplitString(level)+vg.getClass().getSimpleName());
        Log.i(TAG, "printView: "+getSplitString(level)+vg);
        for (int i=0;i<vg.getChildCount();i++){
            if(vg.getChildAt(i) instanceof ViewGroup){
                printView((ViewGroup) vg.getChildAt(i),level+1);
            }else{
                Log.i(TAG, "printView: "+getSplitString(level)+vg.getChildAt(i));
            }
        }
    }

    public String getSplitString(int level){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("|---");
        }
        return sb.toString();
    }
}
