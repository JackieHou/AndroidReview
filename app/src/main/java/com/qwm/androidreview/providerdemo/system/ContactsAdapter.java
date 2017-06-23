package com.qwm.androidreview.providerdemo.system;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
public class ContactsAdapter extends BaseAdapter {

    private List<ContactsBean> mData;
    private Context mContext;
    private LayoutInflater mInflater;

    public ContactsAdapter(Context mContext, List<ContactsBean> mData) {
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
            convertView = mInflater.inflate(R.layout.provider_sys_con_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        ContactsBean bean = mData.get(position);
        holder.pSysItemNameTv.setText(TextUtils.isEmpty(bean.name) ? bean.phone: bean.name);
        holder.pSysItemPhoneTv.setText(bean.phone);
        holder.pSysItemEmailTv.setText(bean.email);
        return convertView;
    }

    public void resetData(List<ContactsBean> list) {
        mData = list;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @Bind(R.id.p_sys_item_header_iv)
        ImageView pSysItemHeaderIv;
        @Bind(R.id.p_sys_item_name_tv)
        TextView pSysItemNameTv;
        @Bind(R.id.p_sys_item_phone_tv)
        TextView pSysItemPhoneTv;
        @Bind(R.id.p_sys_item_email_tv)
        TextView pSysItemEmailTv;
        @Bind(R.id.p_sys_item_ll)
        LinearLayout pSysItemLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
