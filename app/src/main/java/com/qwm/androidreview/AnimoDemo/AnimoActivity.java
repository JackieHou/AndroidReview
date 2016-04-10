package com.qwm.androidreview.animodemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qwm.androidreview.R;
/**
 * @author qiwenming
 * @date 2016/4/7 23:34
 * @ClassName: AnimoActivity
 * @Description:  动画
 */
public class AnimoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animo);
    }

    public void toFrameAnimation(View view){
        startActivity(new Intent(this, FrameAnimActivity.class));
    }

    public void toTweenAnimation(View view){
        startActivity(new Intent(this, TweenAnimActivity.class));
    }

    public void toPropertyAnimation(View view){
        startActivity(new Intent(this, PropertyAnimActivity.class));
    }
}
