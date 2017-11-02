package com.qwm.androidreview.okhttpdemo;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.PermissionListener;
import com.qwm.androidreview.R;
import com.qwm.androidreview.adapter.MyGridAdapter;
import com.qwm.androidreview.gildedemo.GlideBaseTestActivity;
import com.qwm.androidreview.gildedemo.GlideTransformActivity;
import com.qwm.androidreview.gildedemo.GlideTransformThridlibActivity;
import com.qwm.androidreview.view.MyGridView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpDemoActivity extends BaseActivity {

    @Bind(R.id.mgv_content)
    MyGridView mMgvContent;
    @Bind(R.id.okhttp_msg_tv)
    TextView mOkhttpMsgTv;
    private List<String> itemList;

    private static final String TAG = "OkhttpDemoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_okhttp_demo);
        ButterKnife.bind(this);
        initGv();
        //申请权限
        requestPermission();
    }

    private void requestPermission() {
        requestPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001, new PermissionListener() {
            @Override
            public void granted(int requestCode, String[] permissions) {
            }
            @Override
            public void denied(int requestCode, String[] deniedpermissions, String[] permissions) {
                super.denied(requestCode, deniedpermissions, permissions);
                finish();
            }
        });
    }


    private void initGv() {
        itemList = new ArrayList<>();
        itemList.add("同步GET请求");
        itemList.add("异步GET请求");
        itemList.add("异步POST请求");
        itemList.add("异步上传文件");
        itemList.add("异步上传文件(表单方式)");
        itemList.add("异步下载文件");
        itemList.add("异步上传Multipart文件");
        mMgvContent.setAdapter(new MyGridAdapter(this, itemList));
        mMgvContent.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemStr = itemList.get(position);
                if("同步GET请求".equals(itemStr)){
                    syncGet();
                }else if("异步GET请求".equals(itemStr)){
                    ayncGet();
                }else if("异步POST请求".equals(itemStr)){
                    ayncPost();
                }else if("异步上传文件".equals(itemStr)){
                    uploadFile();
                }else if("异步上传文件(表单方式)".equals(itemStr)){
                    uploadFile2();
                }else if("异步下载文件".equals(itemStr)){
                    downloadFile();
                }else if("异步上传Multipart文件".equals(itemStr)){
                    uploadMultipart();
                }
            }
        } );
    }

    /**
     * 同步GET请求
     */
    private void syncGet() {
        String url = "http://192.168.1.121:8080/qwmapi/weather?citys=Beijing&citys=Shenzhen";
        //创建Okhttp客户端
        //创建请求
        //请求网络
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        final Call call = httpClient.newCall(request);
        //子线程
        new Thread(){
            @Override
            public void run() {
                try {
                    final Response response = call.execute();
                    //子线程中请求
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showMsgToTv(response);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 异步GET请求
     */
    private void ayncGet() {
        String url = "http://192.168.1.121:8080/qwmapi/weather?citys=Beijing&citys=Shenzhen";
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                Log.i(TAG, "ayncGet--onResponse: "+Thread.currentThread());
                showMsgToTv(response);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showMsgToTv(response);
                    }
                });
            }
        });
    }

    /**
     * 异步POST请求
     */
    private void ayncPost() {
        String url = "http://192.168.1.121:8080/qwmapi/weather?citys=Beijing&citys=Shenzhen";
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formbody = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formbody)
                .build();
        Call call = okHttpClient.newCall(request);
        //加到队列中去
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                Log.i(TAG, "ayncPost--onResponse: "+Thread.currentThread());
                showMsgToTv(response);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showMsgToTv(response);
                    }
                });
            }
        });
    }

    /**
     * 异步上传文件
     */
    private void uploadFile() {
        String url = "http://192.168.1.121:8080/qwmapi/upload";
        OkHttpClient httpClient = new OkHttpClient();
        File file = new File("/sdcard/test1.jpg");
        MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
        RequestBody body = RequestBody.create(MEDIA_TYPE_MARKDOWN,file);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                Log.i(TAG, "ayncPost--onResponse: "+Thread.currentThread());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showMsgToTv(response);
                    }
                });
            }
        });
    }

    /**
     * 异步上传文件 表单方式上传
     */
    private void uploadFile2() {
        String url = "http://192.168.1.121:8080/qwmapi/upload";
        OkHttpClient httpClient = new OkHttpClient();
        File file = new File("/sdcard/test1.jpg");
        MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image","test1.jpg",
                        RequestBody.create(MEDIA_TYPE_MARKDOWN,file))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                Log.i(TAG, "ayncPost--onResponse: "+Thread.currentThread());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showMsgToTv(response);
                    }
                });
            }
        });
    }

    /**
     * 异步下载文件
     */
    private void downloadFile() {
        String url = "http://192.168.1.121:8080/qwmapi/resources/images/minion_01.png";
        OkHttpClient httpClient= new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream in = response.body().byteStream();
                FileOutputStream fos = null;
                File file = new File("/sdcard/"+System.currentTimeMillis()+".png");
                fos = new FileOutputStream(file);
                byte[] buff = new byte[1024*2];
                int len = 0;
                long contentLenght = response.body().contentLength();
                long current = 0;
                while (( len = in.read(buff) )>0){
                    fos.write(buff,0,len);
                    current +=len;
                    Log.i(TAG, "onResponse:   " +current +"/"+contentLenght+"----------"+( 100.0*current/contentLenght )+"%");
                }
                Log.i(TAG, "onResponse: "+file.getAbsolutePath());
                fos.flush();
                in.close();
                fos.close();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(OkhttpDemoActivity.this,"下载完成了",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /**
     * 异步上传Multipart文件
     */
    private void uploadMultipart() {
        String url = "http://192.168.1.121:8080/qwmapi/upload";
        MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userName","wiming")
                .addFormDataPart("image","test.png",
                        RequestBody.create(MEDIA_TYPE_MARKDOWN,new File("/sdcard/test1.jpg")))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(OkhttpDemoActivity.this,"上传失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showMsgToTv(response);
                        Toast.makeText(OkhttpDemoActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    public void showMsgToTv(final Response response)  {
        try {
            String msg = "code:" + response.code() + "\n";
            if (response.isSuccessful()) {
                msg += response.body().string();
            }
            mOkhttpMsgTv.setText(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
