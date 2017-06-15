package com.qwm.androidreview.receiverdemo.orderreceiver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;
import com.qwm.androidreview.receiverdemo.MyAction;

/**
 * <b>Project:</b> YuantaiApplication<br>
 * <b>Create Date:</b> 2017/6/15<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b>
 *  有序广播
 * <br>
 */
public class OrderReceiverActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_receiver);
    }

    public void onReceiverTest2(View view){
        Intent intent = new Intent(MyAction.MY_ACTION_ORDERTEST1);
        //Intent中的数据，修改不了
        intent.putExtra("test1","intent--data-qwm----test---");

        //携带的数据，能够修改
        Bundle bundle = new Bundle();
        bundle.putString("name","qiwenming");
        bundle.putInt("age",26);
        intent.putExtras(bundle);

        sendOrderedBroadcast(intent,null);
    }
}
