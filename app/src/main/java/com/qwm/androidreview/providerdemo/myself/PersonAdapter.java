package com.qwm.androidreview.providerdemo.myself;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qwm.androidreview.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/6/23<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class PersonAdapter extends BaseAdapter {

    private List<PersonBean> mData;
    private Context mContext;
    private LayoutInflater mInflater;

    public PersonAdapter(Context mContext, List<PersonBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.three_text_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        PersonBean p = mData.get(position);
        holder.threeItem1Tv.setText(" pid "+p.pid);
        holder.threeItem2Tv.setText(" name: "+p.name);
        holder.threeItem3Tv.setText(" age:"+p.age);
        return convertView;
    }

    public void resetData(List<PersonBean> list) {
        mData = list;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @Bind(R.id.three_item_1_tv)
        TextView threeItem1Tv;
        @Bind(R.id.three_item_2_tv)
        TextView threeItem2Tv;
        @Bind(R.id.three_item_3_tv)
        TextView threeItem3Tv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
