package com.qwm.androidreview.pictureoomdemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.qwm.androidreview.R;

import java.util.Arrays;
import java.util.List;

/** @author qiwenming
 * @date 2016/4/6 11:58
 * @ClassName: PictureOOMActivity
 * @Description: 图片OOM一场
 */
public class PictureOOMActivity extends AppCompatActivity {
    private String TAG = PictureOOMActivity.class.getName();
    /**
     * 图片
     */
    private ImageView imageIv;
    private int imageId = R.mipmap.qiwenmingshiwo;
    /**
     * 用户存储图片，缓存
     */
    private LruCache<String,Bitmap> mMemoryCache ;
    private LayoutInflater inflater;
    private ListView contentLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_oom);
        imageIv = (ImageView)findViewById(R.id.iv_image);
        contentLv = (ListView)findViewById(R.id.lv_content);
        inflater = LayoutInflater.from(this);

        //==============缓存=================
        //1.获取应用的最大内存，使用内存不能超过这个值，不然oom
        int maxMemory = (int)(Runtime.getRuntime().maxMemory()/1024);//kB
        //2.创建缓存的图片对象 使用最大可用内存值的1/8作为缓存的大小。 以kB为单位
        mMemoryCache = new LruCache<String,Bitmap>(maxMemory/8){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                //重写此方法，放回图片的大小，默认放回图片的数量
                return bitmap.getByteCount()/1024; //KB
            }
        };

        contentLv.setAdapter(new MyPictureAdapter());
    }

    /**
     * 获取最大内存
     * @param view
     */
    public void getAppMemory(View view){
        int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);//kb
        Toast.makeText(PictureOOMActivity.this, maxMemory+"kB--"+(maxMemory/1024)+"MB", Toast.LENGTH_SHORT).show();
    }

    //==============================================以下是图片压缩====================================================
    /**
     * 压缩和加载图片
     * @param view
     */
    public void zipPicture(View view){
        imageId =  imageId!=R.mipmap.qiwenmingshiwo ? R.mipmap.qiwenmingshiwo : R.mipmap.chuanhunsha;
        imageIv.setImageBitmap(decodeSampledBitmapFromResource(getResources(), imageId, imageIv.getWidth(), imageIv.getHeight()));
    }

    /**
     * 获取bitmap
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,int reqWidth, int reqHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        //第一次解析将inJustDecodeBounds设置为true，来获取图片大小  
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res,resId,options);//获取参数
        //设置计算比例
        options.inSampleSize = calculateInSampleSize(options,reqWidth,reqHeight);
        //-----获取图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res,resId,options);
    }

    /**
     * 计算比例
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public int calculateInSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight){
        //原图片的大小
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        if(width>reqWidth || height>reqHeight){
            int widthRatio = width/reqWidth;
            int heightRatio = height/reqHeight;
            inSampleSize = widthRatio<heightRatio?widthRatio:heightRatio;
        }
//        Log.i(TAG, "原图片："+width+"--"+height);
//        Log.i(TAG, "需求图片："+reqWidth+"--"+reqHeight);
//        Log.i(TAG, "inSampleSize："+inSampleSize);
        return inSampleSize;
    }
    //==============================================以上是图片压缩====================================================

    //==============================================以下是缓存====================================================
    /**
     * 存储图片的rid
     */
    private List<Integer> ridList = Arrays.asList(new Integer[]{
            R.mipmap.sjtp1  ,R.mipmap.sjtp2  ,R.mipmap.sjtp3  ,R.mipmap.sjtp4,
            R.mipmap.sjtp5  ,R.mipmap.sjtp6  ,R.mipmap.sjtp7  ,R.mipmap.sjtp8,
            R.mipmap.sjtp9  ,R.mipmap.sjtp10 ,R.mipmap.sjtp11 ,R.mipmap.sjtp12,
            R.mipmap.sjtp13 ,R.mipmap.sjtp14 ,R.mipmap.sjtp15  ,R.mipmap.sjtp16,
            R.mipmap.sjtp17 ,R.mipmap.sjtp18 ,R.mipmap.sjtp19  ,R.mipmap.sjtp20,
            R.mipmap.sjtp21  ,R.mipmap.sjtp22  ,R.mipmap.sjtp23  ,R.mipmap.sjtp24,
            R.mipmap.sjtp25  ,R.mipmap.sjtp26  ,R.mipmap.sjtp27  ,R.mipmap.sjtp28,
            R.mipmap.sjtp29  ,R.mipmap.sjtp30 ,R.mipmap.sjtp31 ,R.mipmap.sjtp32,
            R.mipmap.sjtp33 ,R.mipmap.sjtp34 ,R.mipmap.sjtp35  ,R.mipmap.sjtp36,
            R.mipmap.sjtp37 ,R.mipmap.sjtp38 ,R.mipmap.sjtp39  ,R.mipmap.sjtp40,
            R.mipmap.sjtp41  ,R.mipmap.sjtp42  ,R.mipmap.sjtp43  ,R.mipmap.sjtp44,
            R.mipmap.sjtp45  ,R.mipmap.sjtp46  ,R.mipmap.sjtp47  ,R.mipmap.sjtp48,
            R.mipmap.sjtp49,
            R.mipmap.bz1,  R.mipmap.bz2,  R.mipmap.bz3,  R.mipmap.bz4,  R.mipmap.bz5,
            R.mipmap.bz6,  R.mipmap.bz7,

    });

    /**
     * 把图片加入到缓存中
     * @param key
     * @param bitmap
     */
    public void addBitmapToMemoryCache(String key,Bitmap bitmap){
        if(getBitmapFromMemoryCache(key)==null){
            mMemoryCache.put(key,bitmap);
        }
    }

    /**
     * 获取键对应的图片
     * @param key
     * @return
     */
    public Bitmap getBitmapFromMemoryCache(String key){
        return mMemoryCache.get(key);
    }

    /**
     * @author qiwenming
     * @date 2016/4/6 23:17
     * @ClassName: PictureOOMActivity
     * @Description:  适配器
     */
    class MyPictureAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return ridList.size();
        }

        @Override
        public Object getItem(int position) {
            return ridList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView iv = null;
            if(convertView==null){
                convertView = inflater.inflate(R.layout.image_item,null);
                iv = (ImageView)convertView.findViewById(R.id.iv_imageitem);
                convertView.setTag(iv);
            }
            iv = (ImageView) convertView.getTag();
            String key = ridList.get(position)+"";
            Bitmap bitmap = mMemoryCache.get(key);
            if(bitmap==null){
                bitmap = decodeSampledBitmapFromResource(getResources(),ridList.get(position),400,400);
                addBitmapToMemoryCache(key,bitmap);
            }
            iv.setImageBitmap(bitmap);
            Log.i(TAG, "mMemoryCache.size:"+mMemoryCache.size());
            return convertView;
        }
    }
}
