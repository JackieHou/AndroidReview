package com.qwm.androidreview;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/3/27<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class BaseFragment extends Fragment {

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
