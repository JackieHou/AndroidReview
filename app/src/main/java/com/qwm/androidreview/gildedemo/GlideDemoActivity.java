package com.qwm.androidreview.gildedemo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;
import com.qwm.androidreview.adapter.MyGridAdapter;
import com.qwm.androidreview.view.MyGridView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * https://github.com/bumptech/glide
 * <p>
 * Glide测试
 * <p>
 * http://blog.csdn.net/guolin_blog/article/details/53759439
 */
public class GlideDemoActivity extends BaseActivity {

    @Bind(R.id.glide_disk_tv)
    TextView mGlideDiskTv;


    private MyGridView contentMgv;

    private ArrayList<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_demo);
        ButterKnife.bind(this);
        initGridView();

        //显示
        showCache();
    }

    public void initGridView() {
        contentMgv = (MyGridView) findViewById(R.id.mgv_content);
        itemList = new ArrayList<>();
        itemList.add("Glide基本测试");
//        itemList.add("Glide变换测试");
        itemList.add("Glide变换三方库");
        contentMgv.setAdapter(new MyGridAdapter(this, itemList));
        contentMgv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemStr = itemList.get(position);
                if ("Glide基本测试".equals(itemStr)) {
                    startActivity(itemStr, GlideBaseTestActivity.class);
                } else if ("Glide变换测试".equals(itemStr)) {
                    startActivity(itemStr,GlideTransformActivity.class);
                }else if ("Glide变换三方库".equals(itemStr)) {
                    startActivity(itemStr,GlideTransformThridlibActivity.class);
                }
            }
        });
    }

    @OnClick(R.id.glide_disk_tv)
    public void showCache() {
        mGlideDiskTv.setText("缓存大小： "+GlideCacheUtil.getInstance().getCacheSize(this));
    }

    @OnClick(R.id.glide_disk_btn)
    public void clearCache() {
        GlideCacheUtil.getInstance().clearImageAllCache(this);
        showCache();
    }
}
