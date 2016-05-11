package com.qwm.androidreview.picassodemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.qwm.androidreview.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by wiming on 2016/4/27.
 */
public class MyAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private  Context mContext;
    private  List<String> mList;

    public MyAdapter(Context context, List<String> list){
        this.mContext = context;
        this.mList =list;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
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
        Picasso.with(mContext).load(mList.get(position)).into(iv);
        return convertView;
    }
}
