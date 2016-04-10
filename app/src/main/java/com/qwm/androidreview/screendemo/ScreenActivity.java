package com.qwm.androidreview.screendemo;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qwm.androidreview.R;
/**
 * @author qiwenming
 * @date 2016/4/11 0:23
 * @ClassName: ScreenActivity
 * @Description:  屏幕适配
 */
public class ScreenActivity extends AppCompatActivity {
    private String TAG = ScreenActivity.class.getName();
    private TextView contentTv;
    private float density = getResources().getDisplayMetrics().density;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        contentTv = (TextView)findViewById(R.id.tv_content);
        int orientation = getResources().getConfiguration().orientation;
        if(orientation==Configuration.ORIENTATION_LANDSCAPE){
            contentTv.setText("现在是横屏");
        }else if(orientation==Configuration.ORIENTATION_PORTRAIT){
            contentTv.setText("现在是竖屏");
        }
        Toast.makeText(ScreenActivity.this, "density:"+density, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onCreate: ------");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: --------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ------------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: -------------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: -----------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: -----------");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i(TAG, "onConfigurationChanged: 横屏ok");
            contentTv.setText("现在是横屏------");
        } else if (this.getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT) {
            Log.i(TAG, "onConfigurationChanged: 竖屏ok");
            contentTv.setText("现在是竖屏------");
        }
    }

    public void swicthDpAndPx(View view){
        float value = 30.5f;
        int dp = px2dip(value);
        int px = dip2px(value);
        String str = "density="+density+"\r\n" +
               value + "px = " + dp + "dp\r\n"+
               value + "dp = " + px + "px\r\n";
    }

    /**
     * dip转px
     * @param dip
     * @return
     */
    public int dip2px(float dip){
        return (int)(dip*density);
    }

    public int px2dip(float px){
      return (int)(px/density);
    }
}
