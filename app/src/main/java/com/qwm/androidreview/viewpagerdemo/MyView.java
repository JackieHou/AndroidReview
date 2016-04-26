package com.qwm.androidreview.viewpagerdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wiming on 2016/4/26.
 */
public class MyView extends View {

    private String TAG = MyView.class.getName();

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    private int angles[] = {30,45,60,120,45,60};
    private int colors[] = {Color.GRAY,Color.MAGENTA,Color.CYAN,Color.GREEN,Color.LTGRAY,Color.YELLOW};

    private List<XmArcBean> xmArcList = new ArrayList<>();

    /**
     * 代表高亮的 扇形的的index
     */
    private int lightArcIndex = -1;
    /**
     * 半径
     */
    private int radius = 300;

    /**
     *圆心坐标
     */
    private int mCenterX = 400;
    private int mCenterY = 500;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        xmArcList.clear();

        int startDegress = 45;//开始角度

        RectF rect = new RectF(mCenterX-radius, mCenterY-radius, mCenterX+radius, mCenterY+radius);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        //开始画  测试我们只画签名几个，因为最有一个我们测试一把移动
        for (int i = 0; i < colors.length; i++) {
            //获取每个扇形拥有的角度和 所画的颜色
            int color = colors[i];
            int angle = angles[i];
            if(lightArcIndex==i){
                //我们移动的值如下
                int tranValue = 30;
                double rAngle = Math.toRadians(90 - (startDegress+angle/2.0f) );
                float tranX = (float)(tranValue * Math.sin(rAngle));
                float tranY =(float)( tranValue * Math.cos(rAngle));
                rect.offset(tranX,tranY);
                paint.setColor(colors[i]);
                canvas.drawArc(rect,startDegress,angle,true,paint);
                rect.offset(-tranX,-tranY);
            }else{//如果不是移动的扇形，那么正常绘制

                //设置颜色
                paint.setColor(color);
                //画扇形
                canvas.drawArc(rect,startDegress,angle,true,paint);
            }
            xmArcList.add(new XmArcBean(i,startDegress,angle));
            //开始角度增加
            startDegress+=angle;
        }

    }

    /**
     * 步骤：
     * 1.对down动作处理
     * 2.获取到当前的点
     * 3.判断这个点在不在我们圆内（计算他到圆心的距离是否小于等于 我们半径）
     * 4.计算出这个点到圆心 和水平方向的夹角
     * 5.根据夹角判断，现在这个点处于哪个扇形内
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //1.就down处理
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //2.获取点
            float pointX = event.getX();
            float pointY = event.getY();
            //3.判断是否在圆内
            if (isInCircle(pointX,pointY)) {
                //4.计算出夹角
                double mRanagle= getAngle(pointX,pointY);
                //5.找出这个夹角属于哪个范围
                lightArcIndex = getArcIndex(mRanagle);
            }else{
                Log.i(TAG, "getAngle:pointX "+pointX+" pointY "+pointY);
                lightArcIndex = -1;
                Log.i(TAG,"点不在范围内");
            }
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    /**
     * 判断这个角度属于哪个范围
     * @param mRanagle
     * @return
     */
    private int getArcIndex(double mRanagle) {
        XmArcBean bean = null;
        for (int i = 0; i < xmArcList.size(); i++) {
            bean = xmArcList.get(i);
            Log.i(TAG, "getArcIndex: "+bean.toString()+"-------"+mRanagle);

            float startAngle = bean.startAngle%360;
            float stopAngle = (bean.startAngle+bean.angle)%360;
            //开始之和结束值都没有超过 360   第二个开始值米有超过360，结束值吵了
            if(  (mRanagle>= startAngle && mRanagle <= stopAngle) ||
                    ( startAngle>stopAngle &&
                      ( (mRanagle>= startAngle && mRanagle <= 360) || ( mRanagle>= 0 && mRanagle <= stopAngle ) ))){
                return i;
            }
        }
        return -1;
    }

    /**
     * 判断点是否在圆内
     * @return
     * @param pointX
     * @param pointY
     */
    public boolean isInCircle(float pointX, float pointY) {
        //判断方式 (x-center_x)**2 + (y-center_y)**2 <= radius**2
        //计算出到圆的位置的平方
        double x2y2 = Math.pow( pointX-mCenterX ,2) +Math.pow(pointY-mCenterY,2);
        double radiusFan = radius*radius;
        return x2y2<=radiusFan;
    }


    /**
     * Fetches angle relative to pie chart center point where 3 O'Clock is 0 and
     * 12 O'Clock is 270degrees
     *
     * @return angle in degress from 0-360.
     */
    public double getAngle(float pointX, float pointY) {
        double dx = pointX - mCenterX;
        // Minus to correct for coord re-mapping
        double dy = -(pointY - mCenterY);

        Log.i(TAG, "getAngle:pointX "+pointX+" pointY "+pointY+"--------- dy:"+dy+"   dx" +dx);

        double inRads = Math.atan2(dy, dx);

        // We need to map to coord system when 0 degree is at 3 O'clock, 270 at 12
        // O'clock
        if (inRads < 0)
            inRads = Math.abs(inRads);
        else
            inRads = 2 * Math.PI - inRads;

        return Math.toDegrees(inRads);
    }

    public class XmArcBean implements Serializable{
        public int id;
        public float startAngle;
        public float angle;

        public XmArcBean(int id, float startAngle, float angle) {
            this.id = id;
            this.startAngle = startAngle;
            this.angle = angle;
        }

        @Override
        public String toString() {
            return "XmArcBean{" +
                    "id=" + id +
                    ", startAngle=" + startAngle +
                    ", angle=" + angle +
                    '}';
        }
    }



    //=====================================最简单的画法==========================================================
    protected void onDraw2(Canvas canvas) {
//        super.onDraw(canvas);

        int x = 300;
        int y = 400;
        int radius = 300;

        int startDegress = 45;//开始角度

        RectF rect = new RectF(x-radius,y-radius,x+radius,y+radius);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        //开始画  测试我们只画签名几个，因为最有一个我们测试一把移动
        for (int i = 0; i < colors.length-1; i++) {
            //获取每个扇形拥有的角度和 所画的颜色
            int color = colors[i];
            int angle = angles[i];
            //设置颜色
            paint.setColor(color);
            //画扇形
            canvas.drawArc(rect,startDegress,angle,true,paint);
            //开始角度增加
            startDegress+=angle;
        }

        //我们移动的值如下
        int tranValue = 30;
        int angle = angles[angles.length-1];
        double rAngle = Math.toRadians(90 - (startDegress+angle/2.0f) );
        float tranX = (float)(tranValue * Math.sin(rAngle));
        float tranY =(float)( tranValue * Math.cos(rAngle));
        rect.offset(tranX,tranY);
        paint.setColor(colors[colors.length-1]);
        canvas.drawArc(rect,startDegress,angle,true,paint);
        rect.offset(-tranX,-tranY);
    }

//========================================最简单的画法=======================================================
    /**
     * 最简单的画法
     * @param canvas
     */
    protected void onDraw1(Canvas canvas) {
//        super.onDraw(canvas);

        int x = 300;
        int y = 400;

        int radius = 300;

        RectF rect = new RectF(x-radius,y-radius,x+radius,y+radius);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        //扇形所在的正方形   开始角度   当前的角度
        canvas.drawArc(rect,30,120,true,paint);

        paint.setColor(Color.GRAY);
        canvas.drawArc(rect,150,200,true,paint);


        paint.setColor(Color.WHITE);
        canvas.drawArc(rect,350,40,true,paint);

    }
}
