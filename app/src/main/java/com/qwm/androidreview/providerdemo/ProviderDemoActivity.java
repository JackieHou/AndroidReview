package com.qwm.androidreview.providerdemo;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.PermissionListener;
import com.qwm.androidreview.R;
import com.qwm.androidreview.providerdemo.myself.ProviderMySelfActivity;
import com.qwm.androidreview.providerdemo.system.ProvideSystemActivity;

/**
 * <b>Project:</b> com.qwm.androidreview<br>
 * <b>Create Date:</b> 2017/6/22<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b>
 * 内容提供者测试
 * <br>
 */
public class ProviderDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_demo);
    }
    
    public void testBySystem(View view){
        //这里面需要申请 短信和电话的权限
        String[] perms = {
                "android.permission.WRITE_SMS",
                Manifest.permission.WRITE_CONTACTS
        };
        requestPermission(perms, 1002, new PermissionListener() {
            @Override
            public void granted(int requestCode, String[] permissions) {
                startActivity("系统提供的内容提供者测试", ProvideSystemActivity.class);
            }
        });
    }

    public void testByMySelf(View view){
        startActivity("自己弄的内容提供者测试", ProviderMySelfActivity.class);
    }
}
