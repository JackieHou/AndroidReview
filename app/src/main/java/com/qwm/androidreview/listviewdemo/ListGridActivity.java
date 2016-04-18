package com.qwm.androidreview.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.qwm.androidreview.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListGridActivity extends AppCompatActivity {

    private ListView listView;

    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_grid);
        listView = (ListView)findViewById(R.id.mlv_list);

        for (int i = 0; i < 20; i++) {
            mList.add("woshixiaoming--"+i);
        }

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mList);

        listView.setAdapter(adapter);
    }
}
