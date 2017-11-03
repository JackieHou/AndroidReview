package com.qwm.androidreview.retrofitdemo;

import android.Manifest;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.PermissionListener;
import com.qwm.androidreview.R;
import com.qwm.androidreview.adapter.MyGridAdapter;
import com.qwm.androidreview.retrofitdemo.request.VideoService;
import com.qwm.androidreview.retrofitdemo.request.VideoService2;
import com.qwm.androidreview.retrofitdemo.response.VideosRespBean;
import com.qwm.androidreview.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDemoActivity extends BaseActivity {


    @Bind(R.id.mgv_content)
    MyGridView mMgvContent;
    @Bind(R.id.retrofit_msg_tv)
    TextView mRetrofitMsgTv;
    private List<String> itemList;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.121:8080/qwmapi/")
            .addConverterFactory(GsonConverterFactory.create())//json转换
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_retrofit_demo);
        ButterKnife.bind(this);
        initGv();
        //申请权限
        requestPermission();
    }

    private void requestPermission() {
        requestPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001, new PermissionListener() {
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
        itemList.add("基本测试");
        itemList.add("基本GET请求");
        itemList.add("异步GET请求");
        itemList.add("异步POST请求");
        itemList.add("异步上传文件");
        itemList.add("异步上传文件(表单方式)");
        itemList.add("异步下载文件");
        itemList.add("异步上传Multipart文件");
        mMgvContent.setAdapter(new MyGridAdapter(this, itemList));
        mMgvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemStr = itemList.get(position);
                if ("基本测试".equals(itemStr)) {
                    baseTest();
                }else if ("基本GET请求".equals(itemStr)) {
                    baseGet();
                } else if ("异步GET请求".equals(itemStr)) {
//                    ayncGet();
                } else if ("异步POST请求".equals(itemStr)) {
//                    ayncPost();
                } else if ("异步上传文件".equals(itemStr)) {
//                    uploadFile();
                } else if ("异步上传文件(表单方式)".equals(itemStr)) {
//                    uploadFile2();
                } else if ("异步下载文件".equals(itemStr)) {
//                    downloadFile();
                } else if ("异步上传Multipart文件".equals(itemStr)) {
//                    uploadMultipart();
                }
            }
        });
    }
    

    /**
     * 基本测试
     */
    private void baseTest() {
        VideoService vs = retrofit.create(VideoService.class);
        Call<ResponseBody> call = vs.getVideo();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                showMsgToTv(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    /**
     * 基本GET请求
     */
    private void baseGet() {
        VideoService2 vs2 = retrofit.create(VideoService2.class);
        Call<VideosRespBean> call = vs2.getVideo();
        call.enqueue(new Callback<VideosRespBean>() {
            @Override
            public void onResponse(Call<VideosRespBean> call, Response<VideosRespBean> response) {
                VideosRespBean vrb = response.body();
                showMsgToTv(vrb.toString());
            }

            @Override
            public void onFailure(Call<VideosRespBean> call, Throwable t) {

            }
        });
    }

    public void showMsgToTv(String msg){
        mRetrofitMsgTv.setText( TextUtils.isEmpty(msg) ? "": msg );
    }

    public void showMsgToTv(final Response response)  {
        try {
            String msg = "code:" + response.code() + "\n";
            if (response.isSuccessful()) {
                msg += ((ResponseBody)response.body()).string();
            }
            mRetrofitMsgTv.setText(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
