package com.qwm.androidreview.receiverdemo.systemreceiver;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;
/**
 * <b>Project:</b> YuantaiApplication<br>
 * <b>Create Date:</b> 2017/4/5<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b>系统广播测试 <br>
 */
public class SystemReceiverActivity extends BaseActivity {

    private JobScheduler mPicJobScheduler;
    public static final int PICTURE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_receiver);
        if(Build.VERSION.SDK_INT >= 21) {
            initService();
        }
//        initPicture();
    }

    private void initService(){
        Intent startServiceIntent = new Intent(this, PictureJobService.class);
        startService(startServiceIntent);
    }

    public void picTest(View view){
        initPicture();
    }

    private void initPicture() {
        if(Build.VERSION.SDK_INT > 21){//m 23  n 24
            mPicJobScheduler = (JobScheduler)getSystemService(Context.JOB_SCHEDULER_SERVICE);
            JobInfo.Builder mPicBuidler = new JobInfo.Builder(PICTURE_CODE,new ComponentName(getPackageName(),PictureJobService.class.getName()));
            mPicBuidler.setPeriodic(3000);
            mPicJobScheduler.schedule(mPicBuidler.build());
        }
    }
}