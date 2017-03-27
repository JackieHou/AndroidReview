package com.qwm.androidreview.animodemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;

/**
 * @author qiwenming
 * @date 2016/4/7 23:41
 * @ClassName: PropertyAnimActivity
 * @Description:  属性动画
 */
public class PropertyAnimActivity extends BaseActivity {

    private TextView textTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim);
        textTv = (TextView)findViewById(R.id.tv_tv1);
    }

    /**
     * 基础测试
     * @param view
     */
    public void testBase(View view){
        ObjectAnimator animator = ObjectAnimator.ofFloat(textTv,"alpha",1.0f,0.1f,0.8f,1.0f);
        animator.setDuration(5000);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Toast.makeText(PropertyAnimActivity.this, "动画结束了", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
