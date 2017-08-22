package com.qwm.androidreview.listviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridView;

/**
 * @author qiwenming
 * @date 2016/4/16 23:08
 * @ClassName: MYGridView
 * @Description:  TODO
 */
public class MyGridView extends GridView{

    private String TAG = MyGridView.class.getName();

    public MyGridView(Context context, AttributeSet attrs) {
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


    public String stringAddZero(String str){
        StringBuilder sb = new StringBuilder();
        for (int i = 32; i > str.length(); i--) {
            sb.append("0");
        }
        sb.append(str);
        return  sb.toString();
    }
}
