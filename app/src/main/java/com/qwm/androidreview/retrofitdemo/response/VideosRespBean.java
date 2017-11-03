package com.qwm.androidreview.retrofitdemo.response;

import java.io.Serializable;
import java.util.List;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/11/3<br>
 * <b>Author:</b> q<br>
 * <b>Description:</b> <br>
 */
public class VideosRespBean extends BaseRespBean {

    public VideosListBean t;

    public class VideosListBean{
        public List<VideosItemBeam> videos;

        @Override
        public String toString() {
            return "VideosListBean{" +
                    "videos=" + videos +
                    '}';
        }
    }

    public class VideosItemBeam implements Serializable{

        /**
         * id : 1
         * image : resources/images/minion_01.png
         * length : 10
         * name : 小黄人 第01部
         * url : resources/videos/minion_01.mp4
         */
        public long id;
        public String image;
        public int length;
        public String name;
        public String url;

        @Override
        public String toString() {
            return "VideosItemBeam{" +
                    "id=" + id +
                    ", image='" + image + '\'' +
                    ", length=" + length +
                    ", name='" + name + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "VideosRespBean{" +
                "t=" + t +
                '}';
    }
}
