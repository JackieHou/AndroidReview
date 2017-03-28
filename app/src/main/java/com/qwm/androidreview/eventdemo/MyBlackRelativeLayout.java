package com.qwm.androidreview.eventdemo;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/3/28<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class MyBlackRelativeLayout extends BaseRelativeLayout {

    private static final String TAG = "MyBlackRelativeLayout";
    public MyBlackRelativeLayout(Context context) {
        super(context);
        setBackgroundColor(Color.BLACK);
    }

    public MyBlackRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.BLACK);
    }

    public MyBlackRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.BLACK);
    }


}
