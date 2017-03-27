package com.qwm.androidreview.viewdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;

/** @author qiwenming
 * @date 2016/4/14 16:38
 * @ClassName: CirclePhotoActivity
 * @Description: 圆形头像测试
 */
public class CirclePhotoActivity extends BaseActivity {

    private ImageView imageIv;
    private ImageView image2Iv;
    private ImageView image3Iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_photo);
        imageIv = (ImageView)findViewById(R.id.iv_image);
        image2Iv = (ImageView)findViewById(R.id.iv_image2);
        image3Iv = (ImageView)findViewById(R.id.iv_image3);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.qiwenmingshiwo);
        imageIv.setImageBitmap(toRoundBitmap(bitmap));
        image2Iv.setImageBitmap(toRoundBitmap2(bitmap));
        image3Iv.setImageBitmap(toRoundBitmap3(bitmap));
    }

    /**
     * 把bitmap剪切为圆形
     * 原理：
     *     下层画一个圆
     *     设置他们的叠加方式为 src_in
     *     上层bitmap
     * @param bitmap
     * @return
     */
    public Bitmap toRoundBitmap(Bitmap bitmap){
        //获取宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //计算边长
        int r = width;
        if(width>height){
            r = height;
        }
        //创建bitmap
        Bitmap backgroundBm = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        //创建一个正方形
        RectF rect = new RectF(0,0,r,r);
        //创建画板
        Canvas canvas = new Canvas(backgroundBm);
        //创建画笔
        Paint paint =new Paint();
        paint.setAntiAlias(true);//出去锯齿

        //画圆
        canvas.drawCircle(r/2,r/2,r/2,paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        //画bitmap
        canvas.drawBitmap(bitmap,null,rect,paint);

        return backgroundBm;
    }

    /**
     * 原理和上面一个一样，
     *    下层 通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，且都等于r/2时，画出来的圆角矩形就是圆形
     *     设置他们的叠加方式为 src_in
     *     上层bitmap
     * @param bitmap
     * @return
     */
    public Bitmap toRoundBitmap2(Bitmap bitmap){
        //获取宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //计算边长
        int r = width;
        if(width>height){
            r = height;
        }
        //创建bitmap
        Bitmap backgroundBm = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        //创建一个正方形
        RectF rectDis = new RectF(0,0,r,r);
        //创建画板
        Canvas canvas = new Canvas(backgroundBm);
        //创建画笔
        Paint paint =new Paint();
        paint.setAntiAlias(true);//出去锯齿

        //画圆角矩形
        canvas.drawRoundRect(rectDis,r/2,r/2,paint);
        //设置模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        paint.setColor(Color.GRAY);
        //bitmap
        canvas.drawBitmap(bitmap,null,rectDis,paint);

        return backgroundBm;
    }


    public Bitmap toRoundBitmap3(Bitmap bitmap){
        //获取宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //计算边长
        int r = width;
        if(width>height){
            r = height;
        }
        //创建bitmap
        Bitmap backgroundBm = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        //创建一个正方形
        RectF rectDis = new RectF(0,0,r,r);
        //创建画板
        Canvas canvas = new Canvas(backgroundBm);
        //创建画笔
        Paint paint =new Paint();
        paint.setAntiAlias(true);//出去锯齿

        //画圆角矩形
        canvas.drawRoundRect(rectDis,r/2,r/2,paint);
        //设置模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        paint.setColor(Color.GRAY);
        //bitmap
        canvas.drawBitmap(bitmap,null,rectDis,paint);
         paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectDis,0.0f,360f,true,paint);
        return backgroundBm;
    }

}
