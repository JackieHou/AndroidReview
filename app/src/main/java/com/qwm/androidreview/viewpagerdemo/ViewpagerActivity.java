package com.qwm.androidreview.viewpagerdemo;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerActivity extends BaseActivity {

    private RectIndicator indecatorRi;
    private ViewPager viewpagerVp;

    private int colors[] = {0xffffffff,0xffff0000,0xff0ff000,0xff00ff00,0xff000ff0,0xff0000ff,
                              0xfff0f0f0,0xff0f0f0f,0xfff00f00,0xfffff000,0xff0fff00,};
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        indecatorRi =  (RectIndicator)findViewById(R.id.ri_indcator);
        viewpagerVp = (ViewPager)findViewById(R.id.vp_viewpager);
        for (int i = 0; i <colors.length; i++) {
            titles.add("第 "+i+" 个");
            TestFragment tf = new TestFragment(colors[i],"第 "+i+" 个");
            fragmentList.add(tf);
        }

        viewpagerVp.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),fragmentList));

        indecatorRi.setIndiWidth(100);
        indecatorRi.setTabItemTitles(titles);
        indecatorRi.setViewPager(viewpagerVp,0);//选中第一个
    }
}
