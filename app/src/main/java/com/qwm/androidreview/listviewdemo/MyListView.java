package com.qwm.androidreview.listviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

/**
 * @author qiwenming
 * @date 2016/4/16 21:20
 * @ClassName: MyListView
 * @Description:  自己定义的ListView
 */
public class MyListView extends ListView {

    private String TAG = MyListView.class.getName();

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        String widthStr = stringAddZero(Integer.toBinaryString(widthMeasureSpec));
        String heightStr = stringAddZero(Integer.toBinaryString(heightMeasureSpec));

        Log.i(TAG, "-----------------onMeasure--------------------------");
        Log.i(TAG, "\twidthMeasureSpec:\t"+widthStr +"    -----"+MeasureSpec.getSize(widthMeasureSpec));
        Log.i(TAG, "\theightMeasureSpec:\t"+heightStr +"    -----"+MeasureSpec.getSize(heightMeasureSpec));

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        String widthSpecStr = stringAddZero(Integer.toBinaryString(getMeasuredWidth()));
        String heightSpecStr = stringAddZero(Integer.toBinaryString(getMeasuredHeight()));

        Log.i(TAG, "-------------------onLayout------------------------");
        Log.i(TAG, "\twidthMeasureSpec:\t"+widthSpecStr +"    -----"+MeasureSpec.getSize(getMeasuredWidth()));
        Log.i(TAG, "\theightMeasureSpec:\t"+heightSpecStr +"    -----"+MeasureSpec.getSize(getMeasuredHeight()));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        String widthStr = stringAddZero(Integer.toBinaryString(getWidth()));
        String heightStr = stringAddZero(Integer.toBinaryString(getHeight()));
        String widthSpecStr = stringAddZero(Integer.toBinaryString(getMeasuredWidth()));
        String heightSpecStr = stringAddZero(Integer.toBinaryString(getMeasuredHeight()));

        Log.i(TAG, "-------------------onDraw------------------------");
        Log.i(TAG, "\twidthMeasureSpec:\t"+widthSpecStr +"    -----"+MeasureSpec.getSize(getMeasuredWidth()));
        Log.i(TAG, "\theightMeasureSpec:\t"+heightSpecStr +"    -----"+MeasureSpec.getSize(getMeasuredHeight()));
        Log.i(TAG, "\twidth:\t"+widthStr +"    -----"+MeasureSpec.getSize(getWidth()));
        Log.i(TAG, "\theight:\t"+heightStr +"    -----"+MeasureSpec.getSize(getHeight()));

    }

    public String stringAddZero(String str){
        StringBuilder sb = new StringBuilder();
        for (int i = 32; i > str.length(); i--) {
            sb.append("0");
        }
        sb.append(str);
        return  sb.toString();
    }
}
