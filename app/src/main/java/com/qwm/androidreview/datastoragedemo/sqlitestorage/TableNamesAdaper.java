package com.qwm.androidreview.datastoragedemo.sqlitestorage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/6/22<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class TableNamesAdaper extends BaseAdapter {

    private List<String> mData;
    private Context mContext;

    public TableNamesAdaper(Context mContext,List<String> mData) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData==null?0:mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = new TextView(mContext);
        }
        ((TextView)convertView).setText(mData.get(position));
        return convertView;
    }

    public void resetData(List<String> list) {
        mData = list;
        notifyDataSetChanged();
    }
}
