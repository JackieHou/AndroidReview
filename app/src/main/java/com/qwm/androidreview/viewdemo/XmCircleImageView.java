package com.qwm.androidreview.viewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * @author qiwenming
 * @date 2016/4/15 23:12 
 * @ClassName: XmCircleImageView
 * @Description:  自己定义的圆形头像
 */
public class XmCircleImageView extends ImageView {

    private String TAG = XmCircleImageView.class.getName();
    private Paint mPaint = new Paint() ;

    public XmCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        BitmapDrawable drawable = (BitmapDrawable)getDrawable();
        Bitmap bitmap = drawable.getBitmap();
         if(drawable==null || bitmap==null){
             return;
         }
        canvas.drawBitmap(toRoundBitmap(bitmap),new Rect(0,0,getWidth(),getHeight()),new Rect(0,0,getWidth(),getHeight()),mPaint);
    }


    /**
     * 创建圆形图片
     * @param bitmap
     * @return
     */
    public Bitmap toRoundBitmap(Bitmap bitmap){
        Bitmap bitmapBg = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);
        //计算边长
        int r = getWidth()>getHeight()?getHeight():getWidth();
        //创建一个矩形
        RectF rect = new RectF(0,0,r,r);

        Canvas canvas = new Canvas(bitmapBg);

        //画圆
        canvas.drawCircle(r/2,r/2,r/2,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //画bitmap
        canvas.drawBitmap(bitmap,null,rect,mPaint);

        //加一个圆圈
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.CYAN);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(r/2,r/2,r/2,mPaint);

        mPaint.reset();

        return bitmapBg;
    }
}
