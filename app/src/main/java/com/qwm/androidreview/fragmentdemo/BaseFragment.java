package com.qwm.androidreview.fragmentdemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qwm.androidreview.PermissionListener;
import com.qwm.androidreview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * 这里的值返回回调其实很简单
 *
 * 1. A--->B
 *    如果A打开B以后，想要回到B关闭以后回传的数据，步骤如下
 *    1.1 A--->B 需要使用 openFragment(B,true),true表示后回调
 *    1.2 B中调用setResualt(bundle)方法，传入要发送的数据即可
 *    1.3 A 中重新 handleCall(bundle)处理回传数据
 *
 *  实现原理是
 *     1. 更具传入的值，判断要不要创建一个回传的监听，如果true,就创建一个监听对象
 *        if(isCall){//需要回调
               fragment.preCallListener = new MyCallListener();
          }
        //这个回调其实就是把值存起来，供onCreateView的方法；里里面判断要不要执行 handleCall方法
         public class MyCallListener{
             public void myResult(Bundle bundle){
                 resultBundle = bundle;
             }
         }
         //4 回调
         if(resultBundle!=null){
              handlerCall(resultBundle);
         }
      2.B中 调用setResualt方法设置值，把这值先保存起来，用于在 onDestory()的方法里面判断 要不要调用功用 A传过来的监听
       public void setResult(Bundle bundle){
          returnBundle = bundle;
       }

     @Override
    public void onDestroy() {
           super.onDestroy();
           //数据不为null,回调不为空，才给你返回数据
           if(returnBundle!=null && preCallListener!=null){//需要回到，那么说明
               preCallListener.myResult(returnBundle);
         }
    }

 *
 *
 */
public abstract class BaseFragment extends Fragment {

    private static final String TAG = "BaseFragment";
    
    /**
     * 保存 打开界面关闭时候，回到这个Fragment的数据，就是回调的数据的
     */
    private Bundle resultBundle;
    /**
     * 这个是上一个界面的回调，如果有这个说明需要回调
     */
    private MyCallListener preCallListener;

    /**
     * 保存需要放回的数，给用户提供一个方法，让其调用 setResualt(),设置放回的结果
     */
    private Bundle returnBundle ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // 1.获取传递过来的参数
        if (getArguments() != null) {
            getArgFromFrag(getArguments());
        }

        // 2.初始化
        initData();

        // 3.初始化View
        View v = initView(inflater, container, savedInstanceState);

        //4 回调
        if(resultBundle!=null){
            handlerCall(resultBundle);
        }

        return v;
    }


    /**
     * 参数的处理
     * @param arguments
     */
    public void getArgFromFrag(Bundle arguments){

    }

    public abstract void initData();

    public abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public void handlerCall(Bundle resultBundle){

    }

    /**
     * 打开Fragment
     * @param fragment
     * @param isCall 是否需要回调
     */
    public void openFragment(BaseFragment fragment, boolean isCall) {
        if(isCall){//需要回调
            fragment.preCallListener = new MyCallListener();
        }
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_content, fragment);
        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commit();
    }

    public void openFragment(BaseFragment fragment){
        openFragment(fragment,false);
    }


    public class MyCallListener{
        public void myResult(Bundle bundle){
            resultBundle = bundle;
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        //数据不为null,回调不为空，才给你返回数据
        if(returnBundle!=null && preCallListener!=null){//需要回到，那么说明
            preCallListener.myResult(returnBundle);
        }
    }

    /**
     * 关闭fragment
     */
    public void closeFragment() {
        if(getFragmentManager()!=null){
            getFragmentManager().popBackStack();
        }else{
            System.out.println("getFragmentManager()==null");
        }
    }

    /**
     * 提供给子类设置放回值的
     * @param bundle
     */
    public void setResult(Bundle bundle){
        returnBundle = bundle;
    }



    //存储我们的回调实例
    private Map<Integer, PermissionListener> mPermissionListenerMap = new HashMap();
    private int requestAginCount = 0;

    /**
     * 单个权限请求
     *
     * @param permission
     * @param requestCode
     * @param permissionListener
     */
    public void requestPermission(String permission, int requestCode, PermissionListener permissionListener) {
        requestPermission(new String[]{permission}, requestCode, permissionListener);
    }

    /**
     * 多个权限的申请
     *
     * @param permissions
     * @param permissionListener
     */
    public void requestPermission(String[] permissions, int requestCode, PermissionListener permissionListener) {
        if (permissions == null || permissions.length <= 0) {
            new NullPointerException("rquest permission must not be null");
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            if (permissionListener != null)
                permissionListener.granted(requestCode, permissions);
            return;
        }
        if (permissionListener != null) {
            mPermissionListenerMap.put(requestCode, permissionListener);
        }
        boolean isRequest = false;
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(getActivity(), permissions[i]) != PackageManager.PERMISSION_GRANTED) {//没有权限
                isRequest = true;
                break;
            }
        }
        if (isRequest) {
            requestPermissions(permissions, requestCode);
        } else if (permissionListener != null) {
            permissionListener.granted(requestCode, permissions);
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.i(TAG, "onRequestPermissionsResult: ");
        final PermissionListener permissionListener = mPermissionListenerMap.get(requestCode);
        if (permissionListener == null)
            return;
        List<String> deniedList = new ArrayList<>();//拒绝的权限

        //循环判断通过的权限和没有通过的权限
        for (int i = 0; i < grantResults.length; i++) {
            String permissionStr = permissions[i];
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                deniedList.add(permissionStr);
            }
        }
        //处理权限
        //判断我们的回调
        //处理权限
        //判断我们的回调
        if(permissions.length>0 && deniedList.size()>0){//含有拒绝
            //这里面我们弹框提示 设置权限
            showSettingPermissionDialog(permissionListener,requestCode);
            permissionListener.denied(requestCode,deniedList.toArray(new String[]{}),permissions);
        } else {//全部通过
            mPermissionListenerMap.remove(requestCode);
            permissionListener.granted(requestCode, permissions);
        }
    }

    private void toPermission(int reqeustCode) {
        Uri packageURI = Uri.parse("package:" + getActivity().getPackageName());
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
        startActivityForResult(intent, reqeustCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PermissionListener permissionListener = mPermissionListenerMap.remove(requestCode);
        requestAginCount++;
        if (permissionListener != null && requestAginCount < 3) {
            permissionListener.onToRequestAggin();
        }
    }

    public void showSettingPermissionDialog(final PermissionListener permissionListener, final int requestCode){
        new AlertDialog.Builder(getActivity())
                .setTitle("权限设置")
                .setMessage("权限不足，无法打开响应的功能哦，是否即打开【权限】“权限>应用权限”")
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toPermission(requestCode);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPermissionListenerMap.remove(requestCode);
                        permissionListener.onCancel();
                    }
                })
                .show();
    }


    //=====================================================================================
}
