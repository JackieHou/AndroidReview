package com.qwm.androidreview.viewpagerdemo;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qwm.androidreview.R;

/**
 * @author qiwenming
 * @date 2016/4/24 11:07
 * @ClassName: TestFragment
 * @Description: 测试
 */
public class TestFragment extends Fragment {


    private int bgColor ;
    private String text;


    public TestFragment() {
    }

    public TestFragment(int bgColor, String text) {
        this.bgColor = bgColor;
        this.text = text;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        view.findViewById(R.id.fl_bg).setBackgroundColor(bgColor);
        ((TextView)view.findViewById(R.id.tv_text)).setText(text);
        return view;
    }

}
