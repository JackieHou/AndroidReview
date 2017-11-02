package com.qwm.androidreview.gildedemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.PermissionListener;
import com.qwm.androidreview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
 /**
   * <b>Project:</b> <br>
   * <b>Create Date:</b> 2017/10/9<br>
   * <b>Author:</b>wiming<br>
   * <b>Description:</b> <br>
  *     基本加载测试
  *
  *      // 加载本地图片
          File file = new File(getExternalCacheDir() + "/image.jpg");
          Glide.with(this).load(file).into(imageView);

          // 加载应用资源
          int resource = R.drawable.image;
          Glide.with(this).load(resource).into(imageView);

          // 加载二进制流
          byte[] image = getImageBytes();
          Glide.with(this).load(image).into(imageView);

          // 加载Uri对象
          Uri imageUri = getImageUri();
          Glide.with(this).load(imageUri).into(imageView);
   */
public class GlideBaseTestActivity extends BaseActivity {

    private final String picUrl = "http://img.qqu.cc/uploads/allimg/141119/2-141119210633.jpg";
    private final String gifUrl = "https://b-ssl.duitang.com/uploads/item/201411/22/20141122124903_tYYds.gif";

    @Bind(R.id.iv1)
    ImageView mIv1;
     @Bind(R.id.iv2)
     ImageView mIv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_base_test);
        ButterKnife.bind(this);
    }

    /**
     * 基本测试
     *
     * @param view
     */
    public void onBaseTest(View view) {
//        Glide.with(this).load(picUrl).placeholder(R.mipmap.ic_launcher).into(mIv1);
        Glide.with(this).load(picUrl).into(mIv1);
    }


     /**
      * 基本测试
      *
      * @param view
      */
     public void onGifTest(View view) {
//         Glide.with(this).load(gifUrl)(R.mipmap.ic_launcher).into(mIv2);
         Glide.with(this).load(gifUrl).into(mIv2);
     }
}
