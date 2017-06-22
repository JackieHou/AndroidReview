package com.qwm.androidreview.datastoragedemo.sqlitestorage;

import android.content.Context;
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
 * <b>Create Date:</b> 2017/6/22<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class DataAdapter extends BaseAdapter {

    private List<PersonBean> mData;
    private Context mContext;
    private LayoutInflater mInflater;

    public DataAdapter(Context mContext, List<PersonBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
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
        if(convertView==null) {
            convertView = mInflater.inflate(R.layout.sqlite_data_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        PersonBean p = mData.get(position);
        holder.sqliteDataItemNameTv.setText(p.name);
        holder.sqliteDataItemAgeTv.setText(p.age+"");
        return convertView;
    }

    public void resetData(List<PersonBean> list) {
        mData = list;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @Bind(R.id.sqlite_data_item_name_tv)
        TextView sqliteDataItemNameTv;
        @Bind(R.id.sqlite_data_item_age_tv)
        TextView sqliteDataItemAgeTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
