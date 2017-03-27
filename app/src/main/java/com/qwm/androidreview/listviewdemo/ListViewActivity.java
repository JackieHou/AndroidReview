package com.qwm.androidreview.listviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;
import com.qwm.androidreview.adapter.MyGridAdapter;

import java.util.ArrayList;

/** @author qiwenming
 * @date 2016/4/5 10:14
 * @ClassName: ListViewActivity
 * @Description: listview的优化
 */
public class ListViewActivity extends BaseActivity {

    private ListView contentLv;
    private ArrayList<String> mList;
    private int page = 1;
    private MyGridAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initListView();
    }

    //======================================lisview分页加载数据=====================================================
    /**
     * 初始化lisview
     */
    public void initListView(){
        contentLv = (ListView)findViewById(R.id.lv_content);
        mList = new ArrayList<String>();
        mList.add("-----------第"+page+"页-----------");
        for (int i = 1; i <= 10; i++) {
            mList.add(""+i);
        }
        myAdapter = new MyGridAdapter(this,mList);
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,mList);
        contentLv.setAdapter(myAdapter);
        contentLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState){
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE://静止状态
                        int position = contentLv.getLastVisiblePosition();
                        if(position==mList.size()-1){
                            fillData();
                        }
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING://惯性滑动

                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://滑动

                        break;
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    public void fillData(){
        page++;
        mList.add("----------第"+page+"页-----------");
        for (int i = 1; i <= 10; i++) {
            mList.add(""+( 10 * (page - 1) + i ));
        }
        myAdapter.resetData(mList);
        myAdapter.notifyDataSetChanged();
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,mList);
//        contentLv.setAdapter(arrayAdapter);
    }


    public void testListView(View view){
        startActivity(new Intent(this,ListGridActivity.class));
    }
    public void testGridView(View view){
        startActivity(new Intent(this,GridViewActivity.class));
    }
}
