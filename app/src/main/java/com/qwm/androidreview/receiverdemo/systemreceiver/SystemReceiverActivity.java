package com.qwm.androidreview.receiverdemo.systemreceiver;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
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

    private JobScheduler mJobScheduler;
    public int pic_job_id = 1;
    public  int video_job_id = 1;
    private ComponentName mPicComp;
    private ComponentName mVideoComp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_receiver);
        if(Build.VERSION.SDK_INT >= 24) {
            initService();
            initParms();
        }
    }


    /**
     * 初始化服务
     */
    private void initService(){
        Intent startServiceIntent = new Intent(this, PictureJobService.class);
        startService(startServiceIntent);
    }

    /**
     * 初始化参数
     */
    private void initParms() {
        mJobScheduler = (JobScheduler)getSystemService(Context.JOB_SCHEDULER_SERVICE);
        mPicComp = new ComponentName(this,PictureJobService.class);
        mVideoComp = new ComponentName(this,VideoJobService.class);
    }

    public void picTest(View view){
        if(Build.VERSION.SDK_INT >= 24){//m 23  n 24
            JobInfo.Builder builder = new JobInfo.Builder(pic_job_id++,mPicComp);
            builder.addTriggerContentUri(new JobInfo.TriggerContentUri(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,JobInfo.TriggerContentUri.FLAG_NOTIFY_FOR_DESCENDANTS));
            mJobScheduler.schedule(builder.build());
        }
    }

    public void videoTest(View view) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            JobInfo.Builder builder = new JobInfo.Builder(video_job_id++,mVideoComp);
            builder.addTriggerContentUri(new JobInfo.TriggerContentUri(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, JobInfo.TriggerContentUri.FLAG_NOTIFY_FOR_DESCENDANTS));
            mJobScheduler.schedule(builder.build());
        }
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this,PictureJobService.class));
        super.onDestroy();
    }
}