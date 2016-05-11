package com.qwm.androidreview.picassodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.qwm.androidreview.R;

import java.util.ArrayList;
import java.util.List;

public class PicassoDemoActivity extends AppCompatActivity {

    private ListView contentLv;
    private List<String> mPicList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_demo);
        contentLv = (ListView)findViewById(R.id.lv_content);

        mPicList.add("http://www.qqzhi.com/uploadpic/2014-07-06/175850495.jpg");
        mPicList.add("http://www.qqzhi.com/uploadpic/2014-07-06/175849505.jpg");
        mPicList.add("http://img1.touxiang.cn/uploads/20140212/12-035043_26.jpg");
        mPicList.add("http://diy.qqjay.com/u2/2015/0427/81f6511ada467b6b2a83d26a5d865f8d.jpg");
        mPicList.add("http://img5.imgtn.bdimg.com/it/u=4234920394,983679887&fm=21&gp=0.jpg");
        mPicList.add("http://img1.touxiang.cn/uploads/20140212/12-035013_592.jpg");
        mPicList.add("http://www.ld12.com/upimg358/allimg/c141128/141GB21241210-2DX8.jpg");
        mPicList.add("http://img5.imgtn.bdimg.com/it/u=2578041021,3538223592&fm=21&gp=0.jpg");
        mPicList.add("http://img1.imgtn.bdimg.com/it/u=926015333,2011608446&fm=21&gp=0.jpg");

        contentLv.setAdapter(new MyAdapter(this,mPicList));
    }

}
