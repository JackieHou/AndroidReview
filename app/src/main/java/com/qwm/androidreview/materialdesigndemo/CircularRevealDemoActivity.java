package com.qwm.androidreview.materialdesigndemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CircularRevealDemoActivity extends BaseActivity {

    @Bind(R.id.test1_btn)
    Button test1Btn;
    @Bind(R.id.test2_btn)
    Button test2Btn;
    @Bind(R.id.test1_ll)
    RelativeLayout test1Ll;
    @Bind(R.id.test2_ll)
    RelativeLayout test2Ll;

    private final int animationTime = 2000;
    @Bind(R.id.test3_ll)
    RelativeLayout test3Ll;
    @Bind(R.id.test4_ll)
    RelativeLayout test4Ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal_demo);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.test1_btn, R.id.test2_btn, R.id.test3_btn, R.id.test4_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test1_btn:
                test(test1Ll);
                break;
            case R.id.test2_btn:
                test(test2Ll);
                break;
            case R.id.test3_btn:
                test(test3Ll,0,0);
                break;
            case R.id.test4_btn:
                test(test4Ll,0,0);
                break;
        }
    }

    private void test(View view) {
        int cx = view.getWidth() / 2;
        int cy = view.getHeight() / 2;
        test(view, cx, cy);
    }

    private void test(View view,int cx,int cy){
        Object obj = view.getTag();
        if (obj != null) {
            boolean isShow = (boolean) obj;
            if (isShow) {
                showView(view,cx,cy);
            } else {
                hideView(view,cx,cy);
            }
            view.setTag(!isShow);
        } else {
            view.setTag(true);
            hideView(view,cx,cy);
        }
    }

//
//    private void showView(View view) {
//        int cx = view.getWidth() / 2;
//        int cy = view.getHeight() / 2;
//        showView(view, cx, cy);
//    }
//
//    private void hideView(final View view) {
//        int cx = view.getWidth() / 2;
//        int cy = view.getHeight() / 2;
//        hideView(view, cx, cy);
//    }

    private void showView(View view, int cx, int cy) {
        int radius = Math.max(view.getWidth(), view.getHeight());
        Animator animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, radius);
        view.setVisibility(View.VISIBLE);
        animator.setDuration(animationTime);
        animator.start();
    }

    private void hideView(final View view, int cx, int cy) {
        int radius = Math.max(view.getWidth(), view.getHeight());
        Animator animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, radius, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
        animator.setDuration(animationTime);
        animator.start();
    }

}
