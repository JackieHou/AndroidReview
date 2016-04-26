package com.qwm.androidreview.eventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by wiming on 2016/4/19.
 */
public class MyButton extends Button{
    private String TAG = MyButton.class.getName();

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        String xy = "x:"+ event.getX()+ "   y:"+event.getX();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "---down: "+xy);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "---move: "+xy);
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "---up: "+xy);
                break;
        }
        return false;
    }
}
