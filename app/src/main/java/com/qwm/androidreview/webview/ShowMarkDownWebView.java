package com.qwm.androidreview.webview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;
import com.qwm.androidreview.constant.TextUrl;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/10/24<br>
 * <b>Author:</b> q<br>
 * <b>Description:</b> <br>
 */
public class ShowMarkDownWebView extends BaseActivity {

    public String loadUrl = TextUrl.mainUrl;

    @Bind(R.id.webview)
    WebView mWebview;
    @Bind(R.id.load_iv)
    ImageView mLoadIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_markdown_webview);
        ButterKnife.bind(this);

        String url = getIntent().getStringExtra("url");
        if (!TextUtils.isEmpty(url)) {
            loadUrl = url;
        }

        //加载中
        Glide.with(this).load(R.drawable.ic_loading).into(mLoadIv);

        webViewSetting();
    }

    private void webViewSetting() {
        //声明WebSettings子类
        WebSettings webSettings = mWebview.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

//        //支持插件
//        webSettings.setPluginsEnabled(true);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        //复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                setTitle("不要怀疑，正在拼了骚命的加载中......");
                mLoadIv.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mLoadIv.setVisibility(View.GONE);
                setTitle(view.getTitle());
            }

        });

        mWebview.loadUrl(loadUrl);
    }

    public static void loadUrl(Activity activity, String loadUrl, String title) {
        if (TextUtils.isEmpty(loadUrl)) {
            return;
        }
        Intent intent = new Intent(activity, ShowMarkDownWebView.class);
        intent.putExtra("url", loadUrl);
        intent.putExtra(ACTIVITY_FROM_TITLE, title);
        activity.startActivity(intent);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && mWebview.canGoBack()) {
            mWebview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
