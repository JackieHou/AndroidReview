package com.qwm.androidreview.gildedemo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;
import com.qwm.androidreview.adapter.MyGridAdapter;
import com.qwm.androidreview.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/11/1<br>
 * <b>Author:</b> q<br>
 * <b>Description:</b> <br>
 */
public class GlideTransformActivity extends BaseActivity {

    public static final String picUrl = "http://files.jb51.net/file_images/game/201511/2015112819302150.png";

    @Bind(R.id.mgv_content)
    MyGridView mMgvContent;
    @Bind(R.id.glide_transform_iv)
    ImageView mGlideTransformIv;
    private List<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_glide_transform);
        ButterKnife.bind(this);
        initGv();
    }

    private void initGv() {
        itemList = new ArrayList<>();
        itemList.add("centerCropTest1");
        itemList.add("centerCropTest2");
        itemList.add("fitCenterTest");
        mMgvContent.setAdapter(new MyGridAdapter(this, itemList));
        mMgvContent.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemStr = itemList.get(position);
                if("centerCropTest1".equals(itemStr)){
                    centerCropTest1();
                }else if("centerCropTest2".equals(itemStr)){
                    centerCropTest2();
                }else if("fitCenterTest".equals(itemStr)){
                    centerCropTest2();
                }
            }
        } );
    }

    private void centerCropTest1(){
        Glide.with(this)
                .load(picUrl)
//                .centerCrop()//
                .into(mGlideTransformIv);
    }

    private void centerCropTest2(){
        Glide.with(this)
                .load(picUrl)
//                .centerCrop()
//                .override(300,300)
                .into(mGlideTransformIv);
    }

    private void fitCenterTest(){
        Glide.with(this)
                .load(picUrl)
//                .fitCenter()//
                .into(mGlideTransformIv);
    }
}
