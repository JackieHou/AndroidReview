package com.qwm.androidreview.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends BaseActivity {

    private List<String> mList = new ArrayList<>();
    private MyGridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        gridView = (MyGridView)findViewById(R.id.mgv_content);

        for (int i = 0; i < 20; i++) {
            mList.add("woshixiaoming--"+i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mList);

        gridView.setAdapter(adapter);
    }
}
