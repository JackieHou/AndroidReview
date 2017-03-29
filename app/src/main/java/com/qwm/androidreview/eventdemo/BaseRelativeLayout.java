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
         float x = ev.getX(actionIndex);
         float y = ev.getY(actionIndex);
//        Log.i(TAG, "dispatchTouchEvent: "+this.getClass().getSimpleName()+"---ev.getActionIndex():"+ev.getActionIndex());

        int count = getChildCount();
        for (int i = count-1; i >=0; i--) {
            View childAt = getChildAt(i);
            //1.能否接受事件
            if(!canViewReceivePointerEvents(childAt))
                continue;
            //2.转换点
            float[] point = transformPointToViewLocal(x,y,childAt);
            //3.判断点在不在控件内
            if(pointInView(point[0],point[1],childAt)){
                Log.i(TAG, "dispatchTouchEvent: "+childAt.getClass().getSimpleName()+"----x:"+point[0]+",y:"+point[1]);
                ev.setLocation(point[0],point[1]);
                return childAt.dispatchTouchEvent(ev);
            }else {
                continue;
            }
        }
        return false;
    }



    private static boolean canViewReceivePointerEvents(View child) {
        return child.getVisibility() == VISIBLE
                || child.getAnimation() != null;
    }

    public float[] transformPointToViewLocal(float x,float y, View child) {
        float point[] = new float[]{x,y};
        point[0] += child.getScrollX() - child.getLeft();
        point[1] += child.getScrollY() - child.getTop();
        String TAG = child.getClass().getSimpleName();
//        Log.i(TAG, "transformPointToViewLocal: (x,y)-->"+"("+x+","+y+")" );
//        Log.i(TAG, "transformPointToViewLocal: Scroll(x,y)-->"+"("+child.getScrollX()+","+child.getScrollY()+")" );
//        Log.i(TAG, "transformPointToViewLocal: getLeft:-->"+child.getLeft()+",getTop:"+child.getTop() );
//        Log.i(TAG, "transformPointToViewLocal: point(x,y)-->"+"("+point[0]+","+point[1]+")" );
        child.getMatrix().mapPoints(point);
        return point;
    }

    final boolean pointInView(float localX, float localY,View view) {
//        Log.i(TAG, "dispatchTouchEvent: "+view.getClass().getSimpleName()+"----getWidth:"+view.getWidth()+",getHeight:"+view.getHeight());
        return localX >= 0 && localX < view.getWidth()
                && localY >= 0 && localY < view.getHeight();
    }
}
