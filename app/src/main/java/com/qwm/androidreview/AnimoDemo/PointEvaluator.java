package com.qwm.androidreview.AnimoDemo;

import android.animation.TypeEvaluator;

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
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        Point point = new Point(x, y);
        return point;
    }

}
