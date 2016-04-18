package com.qwm.androidreview.viewdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.qwm.androidreview.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/** @author qiwenming
 * @date 2016/4/14 17:23
 * @ClassName: ViewActivity
 * @Description: View的说明
 */
public class ViewActivity extends AppCompatActivity {

    private ImageView imageIv;
    private String TAG = ViewActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
//        imageIv = (ImageView)findViewById(R.id.iv_image);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.qiwenmingshiwo);
//        imageIv.setImageBitmap(toRoundBitmap1(bitmap));

        measureData();

    }



    public void measureData(){
        String exactly =  Integer.toBinaryString(View.MeasureSpec.EXACTLY);
        String exactlyF =  Integer.toBinaryString(~View.MeasureSpec.EXACTLY);
        String atmost = Integer.toBinaryString(View.MeasureSpec.AT_MOST);
        String unspecified = Integer.toBinaryString(View.MeasureSpec.UNSPECIFIED);
//        View.MeasureSpec.getMode();
//        View.MeasureSpec.getSize()

        exactly = stringAddZero(exactly);
        exactlyF = stringAddZero(exactlyF);
        atmost = stringAddZero(atmost);
        unspecified = stringAddZero(unspecified);

        Log.i(TAG, "---View.MeasureSpec.EXACTLY："+exactly);
        Log.i(TAG, "---View.MeasureSpec.AT_MOST："+ atmost);
        Log.i(TAG, "---View.MeasureSpec.UNSPECIFIED："+ unspecified);

        Log.i(TAG, "--- ~View.MeasureSpec.EXACTLY："+exactlyF);
    }

    public String stringAddZero(String str){
        StringBuilder sb = new StringBuilder();
        for (int i = 32; i > str.length(); i--) {
            sb.append("0");
        }
        sb.append(str);
        return  sb.toString();
    }

    public void toCirclePhoto(View view){
        startActivity(new Intent(this,CirclePhotoActivity.class));
    }



    public Bitmap toRoundBitmap1(Bitmap bitmap){
        //获取图片的宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //计算正方形边形的半径
        int r = width;
        if(width>height){
            r = height;
        }
        RectF rect = new RectF(0,0,r,r);
        Bitmap backgroundBmp = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(backgroundBmp);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawCircle(r/2,r/2,r/2,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap,null,rect,paint);




        return backgroundBmp;
    }

    public Bitmap toRoundBitmap(Bitmap bitmap) {
        //圆形图片宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //正方形的边长
        int r = 0;
        //取最短边做边长
        if(width > height) {
            r = height;
        } else {
            r = width;
        }
        //构建一个bitmap
        Bitmap backgroundBmp = Bitmap.createBitmap(width,
                height, Bitmap.Config.ARGB_8888);
        //new一个Canvas，在backgroundBmp上画图
        Canvas canvas = new Canvas(backgroundBmp);
        Paint paint = new Paint();
        //设置边缘光滑，去掉锯齿
        paint.setAntiAlias(true);
        //宽高相等，即正方形
        RectF rect = new RectF(0, 0, r, r);
        //通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
        //且都等于r/2时，画出来的圆角矩形就是圆形
        canvas.drawRoundRect(rect, r/2, r/2, paint);
        //设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //canvas将bitmap画在backgroundBmp上
        canvas.drawBitmap(bitmap, null, rect, paint);
        //保存
        saveBitmap(backgroundBmp,"222.png");
        //返回已经绘画好的backgroundBmp
        return backgroundBmp;
    }



    /** 保存方法 */
    public void saveBitmap(Bitmap bm,String picName) {
        Log.e(TAG, "保存图片");
        File f = new File("/sdcard/", picName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Log.i(TAG, "已经保存");
            Toast.makeText(ViewActivity.this, f.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
