package com.qwm.androidreview.gildedemo;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.qwm.androidreview.gildedemo.GlideAdapter.Type;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/11/1<br>
 * <b>Author:</b> q<br>
 * <b>Description:</b> <br>
 */
public class GlideTransformThridlibActivity extends BaseActivity {

    public static final String picUrl = "http://files.jb51.net/file_images/game/201511/2015112819302150.png";
    @Bind(R.id.list)
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_glide_transform2);
        ButterKnife.bind(this);
        initRv();
    }

    private void initRv() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        List<Type> dataSet = new ArrayList<>();
        dataSet.add(Type.Mask);
        dataSet.add(Type.NinePatchMask);
        dataSet.add(Type.CropTop);
        dataSet.add(Type.CropCenter);
        dataSet.add(Type.CropBottom);
        dataSet.add(Type.CropSquare);
        dataSet.add(Type.CropCircle);
        dataSet.add(Type.ColorFilter);
        dataSet.add(Type.Grayscale);
        dataSet.add(Type.RoundedCorners);
        dataSet.add(Type.Blur);
        dataSet.add(Type.Toon);
        dataSet.add(Type.Sepia);
        dataSet.add(Type.Contrast);
        dataSet.add(Type.Invert);
        dataSet.add(Type.Pixel);
        dataSet.add(Type.Sketch);
        dataSet.add(Type.Swirl);
        dataSet.add(Type.Brightness);
        dataSet.add(Type.Kuawahara);
        dataSet.add(Type.Vignette);

        mRecyclerView.setAdapter(new GlideAdapter(this, dataSet));
    }

}
