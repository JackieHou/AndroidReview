package com.qwm.androidreview;

import android.app.Application;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by wiming on 2016/4/27.
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //picasso的缓存设置
//        loadImageCache();
    }


    public static MyApplication getIntance(){
        return mInstance;
    }


//    /**
//     * 如果想自己指定目录，那么像我下面这样就ok啦：
//      */
//    private void loadImageCache() {
//        final String imageCacheDir = getFilesDir() + "/picasso";
//        Picasso picasso = new Picasso.Builder(this).downloader(
//                new OkHttpDownloader(new File(imageCacheDir))).build();
//        Picasso.setSingletonInstance(picasso);
//    }
}
