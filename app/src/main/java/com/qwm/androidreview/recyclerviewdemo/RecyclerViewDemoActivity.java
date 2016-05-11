package com.qwm.androidreview.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.qwm.androidreview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author qiwenming
 * @date 2016/5/12 0:01
 * @ClassName: RecyclerViewDemoActivity
 * @Description: RecyclerView测试
 */
public class RecyclerViewDemoActivity extends AppCompatActivity {


    @Bind(R.id.content_recv)
    RecyclerView contentRecv;
    private List<String> mDatas;
    private MyRecyclerAdapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MyRecyclerNoRuleAdapter myNoRuleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);
        ButterKnife.bind(this);
        initData();
        myAdapter = new MyRecyclerAdapter(this,mDatas);
        myNoRuleAdapter = new MyRecyclerNoRuleAdapter(this,mDatas);
        //设置适配器
        contentRecv.setAdapter(myAdapter);
        //设置布局管理器
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        contentRecv.setLayoutManager(layoutManager);
    }

    protected void initData()
    {
        mDatas = new ArrayList<String>();// 0x21----0x7e
        for (int i = '!'; i <= '~'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recyclermenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_action_listview:
                contentRecv.setAdapter(myAdapter);
                contentRecv.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.id_action_horizontalListview:
                contentRecv.setAdapter(myAdapter);
                layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
                contentRecv.setLayoutManager(layoutManager);
                break;

            case R.id.id_action_gridview:
                contentRecv.setAdapter(myAdapter);
                layoutManager = new GridLayoutManager(this,4);
                contentRecv.setLayoutManager(layoutManager);
                break;
            case R.id.id_action_horizontalGridView:
                contentRecv.setAdapter(myAdapter);
                layoutManager = new GridLayoutManager(this,4,GridLayoutManager.HORIZONTAL,false);
                contentRecv.setLayoutManager(layoutManager);
                break;

            case R.id.id_action_Staggered_v_list:
                contentRecv.setAdapter(myAdapter);
                layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
                contentRecv.setLayoutManager(layoutManager);
                break;
            case R.id.id_action_Staggered_h_list:
                contentRecv.setAdapter(myAdapter);
                layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
                contentRecv.setLayoutManager(layoutManager);
                break;
            case R.id.id_action_Staggered_v_grid:
                contentRecv.setAdapter(myAdapter);
                layoutManager =  new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);;
                contentRecv.setLayoutManager(layoutManager);
                break;
            case R.id.id_action_Staggered_h_grid:
                contentRecv.setAdapter(myAdapter);
                layoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL);;
                contentRecv.setLayoutManager(layoutManager);
                break;
            case R.id.id_action_Staggered_no_rule:
                contentRecv.setAdapter(myNoRuleAdapter);
                layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);;
                contentRecv.setLayoutManager(layoutManager);
                break;



        }
        return true;
    }

}
