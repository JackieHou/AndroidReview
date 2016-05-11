package com.qwm.androidreview.bluetoothdemo;

import android.bluetooth.BluetoothDevice;

import java.io.Serializable;

/**
 * @author qiwenming
 * @date 2016/5/7 21:23
 * @ClassName: DevicesBean
 * @Description:  设备的类
 */
public class DevicesBean implements Serializable{

    public String flag;

    public BluetoothDevice device;

    public DevicesBean(String flag, BluetoothDevice device) {
        this.flag = flag;
        this.device = device;
    }
}
