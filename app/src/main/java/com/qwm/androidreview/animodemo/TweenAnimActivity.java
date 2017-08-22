package com.qwm.androidreview.animodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;

/**
 * @author qiwenming
 * @date 2016/4/7 23:41
 * @ClassName: TweenAnimActivity
 * @Description:  补间动画
 */
public class TweenAnimActivity extends BaseActivity {

    private ImageView imgPic;
    private Animation myAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_anim);
        imgPic = (ImageView)findViewById(R.id.iv_image1);
    }

    /**
     * 位移动画
     * @param view
     */
    public void testTranslate(View view){
        myAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_anim);
        imgPic.startAnimation(myAnimation);
    }

    /**
     * 缩放动画
     * @param view
     */
    public void testScale(View view){
        myAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_anim);
        imgPic.startAnimation(myAnimation);
    }

    /**
     * 旋转动画
     * @param view
     */
    public void testRotate(View view){
        myAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        imgPic.startAnimation(myAnimation);
    }

    /**
     * 透明度动画
     * @param view
     */
    public void testAlpha(View view){
        myAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        imgPic.startAnimation(myAnimation);
    }
    /**
     * 综合
     * @param view
     */
    public void testAll(View view){
        myAnimation = AnimationUtils.loadAnimation(this, R.anim.set_anim);
        imgPic.startAnimation(myAnimation);
    }
}
