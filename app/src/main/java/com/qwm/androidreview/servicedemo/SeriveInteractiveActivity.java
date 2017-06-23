package com.qwm.androidreview.servicedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.qwm.androidreview.R;
import com.qwm.androidreview.datastoragedemo.sqlitestorage.ButtonAdapter;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * <b>Project:</b> YuantaiApplication<br>
 * <b>Create Date:</b> 2017/6/23<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b>
 * 交互方式
 * <br>
 */
public class SeriveInteractiveActivity extends AppCompatActivity {

    @Bind(R.id.serice_inter_gv)
    GridView sericeInterGv;

    private List<String> mBtns = Arrays.asList(
            "广播交互", "共享文件交互", "Messenger交互(信使交互)", "自定义接口交互", "AIDL交互"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serive_interactive);
        ButterKnife.bind(this);
        initGv();
    }

    private void initGv() {
        ButtonAdapter buttonAdapter = new ButtonAdapter(this, mBtns);
        sericeInterGv.setAdapter(buttonAdapter);
        sericeInterGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemStr = mBtns.get(position);
                if ("广播交互".equals(itemStr)) {
                    broadCasetWay();
                } else if ("共享文件交互".equals(itemStr)) {
                    sharedFileWay();
                } else if ("Messenger交互(信使交互)".equals(itemStr)) {
                    messengerWay();
                } else if ("自定义接口交互".equals(itemStr)) {
                    autoDefineInterfaceWay();
                } else if ("AIDL交互".equals(itemStr)) {
                    AIDLWay();
                }
            }
        });
    }

    private void broadCasetWay() {

    }

    private void sharedFileWay() {

    }

    private void messengerWay() {

    }

    private void autoDefineInterfaceWay() {

    }

    private void AIDLWay() {

    }
}
