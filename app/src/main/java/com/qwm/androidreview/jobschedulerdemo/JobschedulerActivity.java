package com.qwm.androidreview.jobschedulerdemo;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.PersistableBundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.BuildConfig;
import com.qwm.androidreview.MainActivity;
import com.qwm.androidreview.R;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * <b>Project:</b> com.qwm.androidreview<br>
 * <b>Create Date:</b> 2017/6/13<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b>
 *
 * 主要实现的功能：
 * 1.控件开始显示红色
 * 2.定时一段时间
 * 3.控件回复颜色
 *
 * Jobscheduler 简单使用
 * 也可看
 *
 * https://github.com/googlesamples/android-JobScheduler
 *
 * 使用步骤
 * http://www.jianshu.com/p/c82ea2333af6
 *
 * 1.创建 JobService
 * 2.清单文件注册 JobService
 * 3.创建 JobScheduler
 * 4.封装任务并执行 JobInfo
 *
 * <br>
 */
public class JobschedulerActivity extends BaseActivity {

    public static final int MSG_UNCOLOR_START = 0;
    public static final int MSG_UNCOLOR_STOP = 1;
    public static final int MSG_COLOR_START = 2;
    public static final int MSG_COLOR_STOP = 3;

    public static final String MESSENGER_INTENT_KEY
            = BuildConfig.APPLICATION_ID + ".MESSENGER_INTENT_KEY";
    public static final String WORK_DURATION_KEY =
            BuildConfig.APPLICATION_ID + ".WORK_DURATION_KEY";

    private EditText mDelayEditText;
    private EditText mDeadlineEditText;
    private EditText mDurationTimeEditText;
    private RadioButton mWiFiConnectivityRadioButton;
    private RadioButton mAnyConnectivityRadioButton;
    private CheckBox mRequiresChargingCheckBox;
    private CheckBox mRequiresIdleCheckbox;

    private ComponentName mServiceComponent;

    private int mJobId = 0;
    private JobScheduler mJobScheduler ;

    //处理来自服务的消息
    private IncomingMessageHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobscheduler);

        // Set up UI.
        mDelayEditText = (EditText) findViewById(R.id.delay_time);
        mDurationTimeEditText = (EditText) findViewById(R.id.duration_time);
        mDeadlineEditText = (EditText) findViewById(R.id.deadline_time);
        mWiFiConnectivityRadioButton = (RadioButton) findViewById(R.id.checkbox_unmetered);
        mAnyConnectivityRadioButton = (RadioButton) findViewById(R.id.checkbox_any);
        mRequiresChargingCheckBox = (CheckBox) findViewById(R.id.checkbox_charging);
        mRequiresIdleCheckbox = (CheckBox) findViewById(R.id.checkbox_idle);

        mJobScheduler = (JobScheduler)getSystemService(Context.JOB_SCHEDULER_SERVICE);
        mServiceComponent = new ComponentName(this,MyJobService.class);
        mHandler = new IncomingMessageHandler(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //开启服务并且提供一个通信用的类示例（Messenger方式）
        Intent startServiceIntent = new Intent(this,MyJobService.class);
        Messenger messengerIncoming = new Messenger(mHandler);
        startServiceIntent.putExtra(MESSENGER_INTENT_KEY,messengerIncoming);
        startService(startServiceIntent);
    }

    @Override
    protected void onStop() {
        stopService(new Intent(this,MyJobService.class));
        super.onStop();
    }


    /**
     * 调度
     */
    public void scheduleJob(View v) {
        JobInfo.Builder builder = new JobInfo.Builder(mJobId++,mServiceComponent);


        String delay = mDelayEditText.getText().toString();
        if (!TextUtils.isEmpty(delay)) {
            //这个函数能让你设置任务的延迟执行时间(单位是毫秒),这个函数与setPeriodic(long time)方法不兼容，如果这两个方法同时调用了就会引起异常；
            builder.setMinimumLatency(Long.valueOf(delay) * 1000);
        }
        String deadline = mDeadlineEditText.getText().toString();
        if (!TextUtils.isEmpty(deadline)) {
            //这个方法让你可以设置任务最晚的延迟时间。如果到了规定的时间时其他条件还未满足，你的任务也会被启动。与setMinimumLatency(long time)一样，这个方法也会与setPeriodic(long time)，同时调用这两个方法会引发异常。
            builder.setOverrideDeadline(Long.valueOf(deadline) * 1000);
        }
        boolean requiresUnmetered = mWiFiConnectivityRadioButton.isChecked();
        boolean requiresAnyConnectivity = mAnyConnectivityRadioButton.isChecked();
        if (requiresUnmetered) {
            //这个方法让你这个任务只有在满足指定的网络条件时才会被执行。默认条件是
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);
        } else if (requiresAnyConnectivity) {
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        }
        //这个方法告诉你的任务只有当用户没有在使用该设备且有一段时间没有使用时才会启动该任务
        builder.setRequiresDeviceIdle(mRequiresIdleCheckbox.isChecked());
        //这个方法告诉你的应用，只有当设备在充电时这个任务才会被执行。
        builder.setRequiresCharging(mRequiresChargingCheckBox.isChecked());

        // Extras, work duration.
        PersistableBundle extras = new PersistableBundle();
        String workDuration = mDurationTimeEditText.getText().toString();
        if (TextUtils.isEmpty(workDuration)) {
            workDuration = "1";
        }
        extras.putLong(WORK_DURATION_KEY, Long.valueOf(workDuration) * 1000);
        builder.setExtras(extras);

        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        mJobScheduler.schedule(builder.build());
    }

    /**
     * 取消全部
     */
    public void cancelAllJobs(View v) {
        mJobScheduler.cancelAll();
        Toast.makeText(JobschedulerActivity.this, "取消了全部", Toast.LENGTH_SHORT).show();
    }

    /**
     * 完成最后一个
     * @param v
     */
    public void finishJob(View v) {
       List<JobInfo> jobInfoList = mJobScheduler.getAllPendingJobs();
        if(jobInfoList.size()>0){
            int jobId = jobInfoList.get(0).getId();
            mJobScheduler.cancel(jobId);
            Toast.makeText(JobschedulerActivity.this, "cancel "+jobId, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(JobschedulerActivity.this, getString(R.string.no_jobs_to_cancel), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 处理从服务回来的消息的 Handler
     */
    private static class IncomingMessageHandler extends Handler {

        // Prevent possible leaks with a weak reference.
        private WeakReference<JobschedulerActivity> mActivity;

        IncomingMessageHandler(JobschedulerActivity activity) {
            super(/* default looper */);
            this.mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            JobschedulerActivity mainActivity = mActivity.get();
            if (mainActivity == null) {
                // Activity is no longer available, exit.
                return;
            }
            View showStartView = mainActivity.findViewById(R.id.onstart_textview);
            View showStopView = mainActivity.findViewById(R.id.onstop_textview);
            Message m;
            switch (msg.what) {
                /*
                 * Receives callback from the service when a job has landed
                 * on the app. Turns on indicator and sends a message to turn it off after
                 * a second.
                 */
                case MSG_COLOR_START:
                    // Start received, turn on the indicator and show text.
                    showStartView.setBackgroundColor(getColor(R.color.green));
                    updateParamsTextView(msg.obj, "started");

                    // Send message to turn it off after a second.
                    m = Message.obtain(this, MSG_UNCOLOR_START);
                    sendMessageDelayed(m, 1000L);
                    break;
                /*
                 * Receives callback from the service when a job that previously landed on the
                 * app must stop executing. Turns on indicator and sends a message to turn it
                 * off after two seconds.
                 */
                case MSG_COLOR_STOP:
                    // Stop received, turn on the indicator and show text.
                    showStopView.setBackgroundColor(getColor(R.color.red));
                    updateParamsTextView(msg.obj, "stopped");

                    // Send message to turn it off after a second.
                    m = obtainMessage(MSG_UNCOLOR_STOP);
                    sendMessageDelayed(m, 2000L);
                    break;
                case MSG_UNCOLOR_START:
                    showStartView.setBackgroundColor(getColor(R.color.gray_999999));
                    updateParamsTextView(null, "");
                    break;
                case MSG_UNCOLOR_STOP:
                    showStopView.setBackgroundColor(getColor(R.color.gray_999999));
                    updateParamsTextView(null, "");
                    break;
            }
        }

        private void updateParamsTextView(@Nullable Object jobId, String action) {
            TextView paramsTextView = (TextView) mActivity.get().findViewById(R.id.task_params);
            if (jobId == null) {
                paramsTextView.setText("");
                return;
            }
            JobParameters jobP = (JobParameters)jobId;
            String jobIdText = String.valueOf(jobP.getJobId());
            paramsTextView.setText(String.format("Job ID %s %s", jobIdText, action));
        }

        private int getColor(@ColorRes int color) {
            return mActivity.get().getResources().getColor(color);
        }
    }
}
