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
public class ButtonAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<String> mData;
    private final LayoutInflater inflater;

    public ButtonAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mData = data;
        this.inflater = LayoutInflater.from(mContext);
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
            convertView = inflater.inflate(R.layout.sqlite_button_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.sqliteButtonItemTv.setText(mData.get(position));
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.sqlite_button_item_tv)
        TextView sqliteButtonItemTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
