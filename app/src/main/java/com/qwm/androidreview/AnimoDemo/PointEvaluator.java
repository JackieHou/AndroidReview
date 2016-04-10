package com.qwm.androidreview.animodemo;

import android.animation.TypeEvaluator;

import java.util.Random;

/** @author qiwenming
 * @date 2016/4/8 17:19
 * @ClassName: PointEvaluator
 * @Description:
 */
public class PointEvaluator implements TypeEvaluator {

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        Random random = new Random();
//        float x = random.nextInt(600);
//        float y =x;
//        Log.i("pointevaluator", "evaluate: "+x);
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        Point point = new Point(x, y);
        return point;
    }

}
