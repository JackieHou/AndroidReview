package com.qwm.androidreview.listazdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qwm.androidreview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qiwenming
 * @date 2016/4/21 16:35
 * @ClassName: AZAdapter
 * @Description:  A-Z的适配器
 */
public class AZAdapter extends BaseAdapter {

    private Context mContext;
    private List<AZBean> mList;
    private LayoutInflater inflater;
    /**
     * 存储着字符的位置
     */
    private Map<String,Integer> locationMap = new HashMap<String,Integer>();

    public AZAdapter(Context mContext, List<AZBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        this.inflater = LayoutInflater.from(mContext);
        initLocationMap();
    }

    public void setList(List<AZBean> mList){
        this.mList = mList;
        initLocationMap();
    }

    /**
     * 计算我们的 字母对应的位置
     */
    private void initLocationMap(){
        //清楚所有的
        locationMap.clear();
        for (int i = 0; i < mList.size(); i++) {
            String str = mList.get(i).letter.toUpperCase();
            if(!locationMap.containsKey(str)){//不包含加进去
                locationMap.put(str,i);
            }
        }
    }

    public Map<String,Integer> getLocationMap(){
        return locationMap;
    }

    /**
     * 更具字母获取值
     * @param str
     * @return
     */
    public int getLocation(String str){
        Integer locaiton = locationMap.get(str.toUpperCase());
        return locaiton==null?-1:locaiton;
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
        ViewHolder viewHolder = null;
        if(convertView==null){
            viewHolder = new ViewHolder();
            View view = inflater.inflate(R.layout.azlistview_item,null);
            viewHolder.title = (TextView) view.findViewById(R.id.tv_title);
            viewHolder.content = (TextView) view.findViewById(R.id.tv_content);
            convertView = view;
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        AZBean item = mList.get(position);
        if(position==0){
            viewHolder.title.setVisibility(View.VISIBLE);
        }else{
            if(mList.get(position-1).letter.equals(item.letter)){//上一个和这一个相等，不显示
                viewHolder.title.setVisibility(View.GONE);
            }else{
                viewHolder.title.setVisibility(View.VISIBLE);
            }
        }
        viewHolder.title.setText(item.letter);
        viewHolder.content.setText(item.content);
        return convertView;
    }


    class ViewHolder {
        TextView title;
        TextView content;
    }

}
