package com.qwm.androidreview.utils;

import android.content.Context;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/11/1<br>
 * <b>Author:</b> q<br>
 * <b>Description:</b> <br>
 */
public class DimenUtils {
    public static int dip2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
