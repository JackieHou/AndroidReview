package com.qwm.androidreview.recyclerviewdemo;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qwm.androidreview.R;

import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wiming on 2016/5/12.
 */
public class MyRecyclerNoRuleAdapter extends RecyclerView.Adapter<MyRecyclerNoRuleAdapter.MyViewHolder> {

    private final LayoutInflater mInflater;
    private List<String> mList;
    private Context mContext;

    private int colors[] = {Color.BLACK, Color.MAGENTA,Color. YELLOW,Color.LTGRAY,
                              Color.GREEN,Color.BLUE,Color.DKGRAY,Color.CYAN,
                              Color.RED,Color.WHITE,Color.argb(0xff,0x45,0xff,0xff)};

    public MyRecyclerNoRuleAdapter(Context mContext, List<String> mList) {
        this.mList = mList;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder = new MyViewHolder(mInflater.inflate(R.layout.recycler_test_item, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textTv.setText(mList.get(position));
        Random random = new Random();

        int colorIndex = random.nextInt(colors.length-1);
        colorIndex = colorIndex<0?0:colorIndex;
        holder.contentLl.setBackgroundColor(colors[colorIndex]);

        int height = random.nextInt(600);
        height = height<90?height+90:height;
        holder.textTv.setHeight(height);
        colorIndex = (colorIndex+1)%colors.length;
        holder.textTv.setTextColor(colors[colorIndex]);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder  extends RecyclerView.ViewHolder {
        @Bind(R.id.text_tv)
        TextView textTv;
        @Bind(R.id.content_ll)
        LinearLayout contentLl;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
