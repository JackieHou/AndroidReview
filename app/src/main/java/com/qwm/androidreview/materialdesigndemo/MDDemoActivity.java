package com.qwm.androidreview.materialdesigndemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.qwm.androidreview.R;
import com.qwm.androidreview.adapter.MyGridAdapter;
import com.qwm.androidreview.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: qiwenming
 * @date: 16/6/18 下午11:03
 * @className: MDDemoActivity
 * @description: mddemo
 */
public class MDDemoActivity extends AppCompatActivity {


    @Bind(R.id.mgv_content)
    MyGridView mgvContent;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mddemo);
        ButterKnife.bind(this);
        mgvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String posStr = list.get(position);
                if ("Touch feedback".equals(posStr)) {
                    startActivity(new Intent(MDDemoActivity.this, TouchFeedbackActivity.class));
                }else if ("Circular Reveal".equals(posStr)) {
                    startActivity(new Intent(MDDemoActivity.this, CircularRevealDemoActivity.class));
                }
            }
        });
        mgvContent.setAdapter(new MyGridAdapter(this,getList()));
    }

    public List<String> getList() {
        list = new ArrayList<>();
        list.add("Touch feedback");
        list.add("Circular Reveal");
        return list;
    }
}
