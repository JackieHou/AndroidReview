package com.qwm.androidreview.providerdemo.system;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qwm.androidreview.R;
import com.qwm.androidreview.utils.TimeUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/6/23<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class SmsAdapter extends BaseAdapter {

    private List<SmsBean> mData;
    private Context mContext;
    private LayoutInflater mInflater;

    public SmsAdapter(Context mContext, List<SmsBean> mData) {
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
            convertView = mInflater.inflate(R.layout.provider_sys_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SmsBean sms = mData.get(position);
        if(sms.type == 2){//设置背景，头像，号码时候为粗体
            holder.pSysItemLl.setBackgroundColor(Color.LTGRAY);
            holder.pSysItemHeaderIv.setImageResource(R.mipmap.ic_my);
            holder.pSysItemPhoneTv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }else{
            holder.pSysItemLl.setBackgroundColor(Color.MAGENTA);
            holder.pSysItemHeaderIv.setImageResource(R.mipmap.ic_her);
            holder.pSysItemPhoneTv.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
        //设置电话文字和时间
        holder.pSysItemPhoneTv.setText(sms.address);
        holder.pSysItemMsgTv.setText(sms.body);
        holder.pSysItemTimeTv.setText(TimeUtils.longToString(sms.date,"MM/dd HH:mm:ss"));
        return convertView;
    }

    public void resetData(List<SmsBean> smsBeanList) {
        mData = smsBeanList;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @Bind(R.id.p_sys_item_header_iv)
        ImageView pSysItemHeaderIv;
        @Bind(R.id.p_sys_item_phone_tv)
        TextView pSysItemPhoneTv;
        @Bind(R.id.p_sys_item_msg_tv)
        TextView pSysItemMsgTv;
        @Bind(R.id.p_sys_item_time_tv)
        TextView pSysItemTimeTv;
        @Bind(R.id.p_sys_item_ll)
        LinearLayout pSysItemLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
