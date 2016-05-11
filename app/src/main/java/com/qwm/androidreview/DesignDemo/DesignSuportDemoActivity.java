package com.qwm.androidreview.DesignDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.qwm.androidreview.R;
import com.qwm.androidreview.adapter.MyGridAdapter;
import com.qwm.androidreview.recyclerviewdemo.RecyclerViewDemoActivity;
import com.qwm.androidreview.view.MyGridView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DesignSuportDemoActivity extends AppCompatActivity {

    @Bind(R.id.mgv_content)
    MyGridView mgvContent;
    private ArrayList<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_suport_demo);
        ButterKnife.bind(this);
        initGridView();
    }

    private void initGridView() {
        itemList = new ArrayList<String>();
        itemList.add("RecyclerView测试");
        itemList.add("Snackbar测试");
        mgvContent.setAdapter(new MyGridAdapter(this, itemList));
        mgvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemStr = itemList.get(position);
                if ("RecyclerView测试".equals(itemStr)) {
                    startActivity(new Intent(DesignSuportDemoActivity.this, RecyclerViewDemoActivity.class));
                } else if ("Snackbar".equals(itemStr)) {

                }
            }
        });
    }
}
