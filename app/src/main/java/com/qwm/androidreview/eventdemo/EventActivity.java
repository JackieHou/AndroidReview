package com.qwm.androidreview.eventdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.qwm.androidreview.R;

public class EventActivity extends AppCompatActivity {

    private String TAG = EventActivity.class.getName();

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        btn = (Button)findViewById(R.id.btn_button);

        btn.setOnTouchListener(new MyOnTouchListener());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: -------");
            }
        });

    }


    class MyOnTouchListener implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
           String xy = "x:"+ event.getX()+ "   y:"+event.getX();
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    Log.i(TAG, "---down: "+xy);
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.i(TAG, "---move: "+xy);
                    break;
                case MotionEvent.ACTION_UP:
                    Log.i(TAG, "---up: "+xy);
                    break;
            }
            return false;
        }
    }
}
