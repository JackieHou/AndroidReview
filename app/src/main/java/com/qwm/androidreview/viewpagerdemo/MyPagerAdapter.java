package com.qwm.androidreview.viewpagerdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by wiming on 2016/4/24.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {


    public MyPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    // Fragment集合
    private List<Fragment> list;


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
