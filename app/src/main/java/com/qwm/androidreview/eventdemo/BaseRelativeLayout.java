package com.qwm.androidreview.eventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/3/28<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class BaseRelativeLayout extends RelativeLayout {
    private static final String TAG = "BaseRelativeLayout";
    public BaseRelativeLayout(Context context) {
        super(context);
    }

    public BaseRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int actionIndex = ev.getActionIndex();
        final float x = ev.getX(actionIndex);
        final float y = ev.getY(actionIndex);
        Log.i(TAG, "dispatchTouchEvent: "+this.getClass().getSimpleName()+"---ev.getActionIndex():");

        int count = getChildCount();
        for (int i = count-1; i >=0; i--) {
            View childAt = getChildAt(i);
            //1.能否接受事件
            if(!canViewReceivePointerEvents(childAt))
                return false;
            //2.转换点
            float[] point = transformPointToViewLocal(x,y,childAt);

            //3.判断点在不在控件内
            if(pointInView(point[0],point[1],childAt)){
                return childAt.dispatchTouchEvent(ev);
            }else {
                continue;
            }
        }

        return true;
    }



    private static boolean canViewReceivePointerEvents(View child) {
        return child.getVisibility() == VISIBLE
                || child.getAnimation() != null;
    }

    public float[] transformPointToViewLocal(float x,float y, View child) {
        float point[] = new float[]{x,y};
        point[0] += child.getScrollX() - child.getLeft();
        point[1] += child.getScrollY() - child.getTop();
        return point;
    }

    final boolean pointInView(float localX, float localY,View view) {
        return localX >= 0 && localX < view.getWidth()
                && localY >= 0 && localY < view.getHeight();
    }
}
