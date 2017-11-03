package com.qwm.androidreview.retrofitdemo.request;

import com.qwm.androidreview.retrofitdemo.response.VideosRespBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/11/2<br>
 * <b>Author:</b> q<br>
 * <b>Description:</b> <br>
 *  视频列表
 */
public interface VideoService2 {
    @GET("video")
    Call<VideosRespBean> getVideo();
}
