package com.qwm.androidreview.asyncdemo;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledFuture;

/**
 * @author qiwenming
 * @date 2016/4/19 11:46
 * @ClassName: AsyncActivity
 * @Description: 测试异步
 */
public class AsyncActivity extends BaseActivity {

    private String TAG = AsyncActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.i(TAG, "handleMessage: --- boolean handleMessage(Message msg)----");
            Toast.makeText(AsyncActivity.this, "handleMessage: --- boolean handleMessage(Message msg)----", Toast.LENGTH_SHORT).show();
            return false;
        }
    }) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(AsyncActivity.this, "handleMessage: --- void handleMessage(Message msg)----", Toast.LENGTH_LONG).show();
            Log.i(TAG, "handleMessage: --void handleMessage(Message msg)----");
        }
    };


    /**
     * handler测试
     *
     * @param view
     */
    public void testHandler(View view) {
        Log.i(TAG, "testHandler: ");
        mHandler.sendEmptyMessage(1002);
    }

    /**
     * AscTask测试
     *
     * @param view
     */
    public void testAscTask(View view) {
        asyT.execute(1);
    }

    //--------------------------
    //Params, Progress, Result
    private AsyncTask<Integer, Integer, Integer> asyT = new AsyncTask<Integer, Integer, Integer>(){

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG, "onPreExecute: ---"+Thread.currentThread());
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            Log.i(TAG, "doInBackground: ----"+Thread.currentThread());
            int current = params[0];
            while(current<10){
                try {
                    Thread.sleep(500);
                    current++;
                    publishProgress(current);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return current;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.i(TAG, "onPostExecute: ----"+Thread.currentThread()+"-------"+integer);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.i(TAG, "onProgressUpdate: ----"+Thread.currentThread()+"------"+values[0]);
        }
    };

//    FutureTask   Callable


}
