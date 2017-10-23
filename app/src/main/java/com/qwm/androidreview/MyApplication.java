package com.qwm.androidreview;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

/**
 * Created by wiming on 2016/4/27.
 */
public class MyApplication extends MultiDexApplication {
    private static MyApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //picasso的缓存设置
//        loadImageCache();
        //初始化环信
        initEMC();
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

    /**
     * 初始化环信
     */
    public void initEMC(){
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);

        //初始化
        EMClient.getInstance().init(mInstance, options);

        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }
}
