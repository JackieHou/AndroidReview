package com.qwm.androidreview.bluetoothdemo;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qwm.androidreview.R;

import java.util.List;

/**
 * Created by wiming on 2016/5/7.
 * 设备的适配器
 */
public class DevicesAdapter extends BaseAdapter {

    private Context mContext;
    private List<DevicesBean> mList;
    private LayoutInflater inflater;

    public DevicesAdapter(Context mContext, List<DevicesBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
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
        ViewHolder holder = null;
        if(convertView==null){
            holder = new ViewHolder();
            View view = inflater.inflate(R.layout.bluetooth_item,null);
            holder.titleTv = (TextView) view.findViewById(R.id.tv_title);
            holder.nameTv = (TextView) view.findViewById(R.id.tv_name);
            holder.addressTv = (TextView) view.findViewById(R.id.tv_address);
            view.setTag(holder);
            convertView = view;
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        DevicesBean bean = mList.get(position);
        if(position==0 || !bean.flag.equals(mList.get(position-1).flag)){
            holder.titleTv.setVisibility(View.VISIBLE);
            holder.titleTv.setText(bean.flag);
        }else {
            holder.titleTv.setVisibility(View.GONE);
        }
        holder.nameTv.setText(bean.device.getName());
        holder.addressTv.setText(bean.device.getAddress());
        return convertView;
    }

    public void resetData(List<DevicesBean> devicesList) {
        mList = devicesList;
        notifyDataSetChanged();
    }

    class ViewHolder{
        TextView titleTv;
        TextView nameTv;
        TextView addressTv;
    }
}
