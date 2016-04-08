package com.qwm.androidreview.AnimoDemo;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.qwm.androidreview.R;

/**
 * @author qiwenming
 * @date 2016/4/7 23:40
 * @ClassName: FrameAnimActivity
 * @Description: 帧动画
 */
public class FrameAnimActivity extends AppCompatActivity {

    private ImageView imageIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_anim);
        imageIv = (ImageView)findViewById(R.id.iv_image1);
    }

    public void testAnimation(View view){
       AnimationDrawable animationDrawable = (AnimationDrawable)imageIv.getBackground();
        animationDrawable.start();
    }
}
