package com.qwm.androidreview.vollydemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;

import org.json.JSONObject;

/**
 * @author qiwenming
 * @date 2016/4/19 20:20
 * @ClassName: VolleyActivity
 * @Description:  Volley测试
 */
public class VolleyActivity extends BaseActivity {

    private TextView contentTv;
    private ImageView iv1;
    private ImageView iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        contentTv  = (TextView) findViewById(R.id.tv_content);
        iv1  = (ImageView) findViewById(R.id.iv_image1);
        iv2  = (ImageView) findViewById(R.id.iv_image2);
    }


    /**
     * 测试
     * @param view
     */
    public void test(View view){
//        testStringQueue();
        testImageRquest();
        testImageLoader();
        testJsonRequest();
    }


    /**
     * StringRequest
     */
    public void testStringQueue(){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                contentTv.setText(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                contentTv.setText(volleyError.getLocalizedMessage());
            }
        });
        queue.add(stringRequest);
    }


    /**
     * JsonRequest
     */
    public void testJsonRequest(){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObRequest = new JsonObjectRequest(Request.Method.GET, "http://www.weather.com.cn/data/sk/101010100.html", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                contentTv.setText(jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                contentTv.setText(volleyError.getLocalizedMessage());
            }
        });
        queue.add(jsonObRequest);
    }


    public void testImageRquest(){
        RequestQueue queue = Volley.newRequestQueue(this);
        ImageRequest imageRequest = new ImageRequest("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                iv1.setImageBitmap(bitmap);
            }
        }, 200, 200, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                contentTv.setText(volleyError.getLocalizedMessage());
            }
        });
        queue.add(imageRequest);
    }

    public void testImageLoader(){
//        1. 创建一个RequestQueue对象。
//        2. 创建一个ImageLoader对象。
//        3. 获取一个ImageListener对象。
//        4. 调用ImageLoader的get()方法加载网络上的图片。
        RequestQueue queue = Volley.newRequestQueue(this);
        ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String s) {
                return null;
            }
            @Override
            public void putBitmap(String s, Bitmap bitmap) {
            }
        });

        ImageLoader.ImageListener imageListener =ImageLoader.getImageListener(iv2,R.mipmap.qiwenmingshiwo,R.drawable.ic_launcher);

        imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",imageListener,200,200);
    }

}
