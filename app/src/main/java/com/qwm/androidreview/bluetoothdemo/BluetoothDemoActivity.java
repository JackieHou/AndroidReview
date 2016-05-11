package com.qwm.androidreview.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qwm.androidreview.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
/**
 * @author qiwenming
 * @date 2016/5/7 21:48
 * @ClassName: BluetoothDemoActivity
 * @Description:  蓝牙测试
 */
public class BluetoothDemoActivity extends AppCompatActivity {

    private static final String TAG = "BluetoothDemoActivity";
    private BluetoothAdapter mBtAdapter;
    private ListView devicesLv;
    private List<DevicesBean> devicesList = new ArrayList<>();

    private UUID MY_UUID;
    private String NAME;


    // Unique UUID for this application
    private static final UUID MY_UUID_SECURE =
            UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
    private static final UUID MY_UUID_INSECURE =
            UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");

    private DevicesAdapter myAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_demo);
        initLv();
        openBluetooth();
    }

    private void initLv() {
        devicesLv =  (ListView)findViewById(R.id.lv_listview);
        devicesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DevicesBean bean = devicesList.get(position);
                if("paired".equals(bean.flag)){//链接操作
                    connectDevice(bean.device);
                }else{//配对操作

                }
            }


        });
    }


    /**
     * 链接
     * @param device
     */
    private void connectDevice(BluetoothDevice device) {
        MY_UUID = UUID.fromString(device.getUuids()[0]+"");
        NAME = device.getName();
        new AcceptThread().start();
    }




    /**
     * 打开蓝牙
     */
    public void openBluetooth() {
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBtAdapter != null) {//BluetoothAdapter代表本地蓝牙设备，如果为null说明不支持蓝牙设备
            //判断当前的蓝牙是否已经打开，如果不打开，那么打开它
            if (!mBtAdapter.isEnabled()) {
                //两种方式打开，一种是强制打开，一种是用户选择
                //1.强制打开
//                mBtAdapter.enable();
                //2.用户选择
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivity(enableIntent);
            }
        } else {
            Log.i(TAG, "openBluetooth: 不支持蓝牙设备");
        }
    }

    /**
     * 发现设备
     *
     * @param view
     */
    public void onFindDevices(View view) {
        devicesList.clear();
        if (mBtAdapter == null)
            return;
        Set<BluetoothDevice> pariedDevices = mBtAdapter.getBondedDevices();
        Iterator<BluetoothDevice> it = pariedDevices.iterator();
        while (it.hasNext()) {
            BluetoothDevice device = it.next();
            devicesList.add(new DevicesBean("paired",device));
            Log.i(TAG, "----paire:  name:" + device.getName() + "   Address" + device.getAddress()+"   uuid"+device.getUuids()[0]);
        }
        showDevices();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);
        mBtAdapter.startDiscovery();
    }

    /**
     * 发现蓝牙设备
     */
    public void myDiscover(View view) {
//        if (mBtAdapter == null)
//            return;

        //1.获取 指令字符串
        String cmdstr = StringUtils.getCmd(0x1b,0,0);
        //2.获取指令字符串对应的 二进制数组
        byte[] bytes = StringUtils.hexStringToBytes(cmdstr);
        connectedThread.write(bytes);

    }


    //--------------关闭发现蓝牙设备----------------
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Make sure we're not doing discovery anymore
        if (mBtAdapter != null) {
            mBtAdapter.cancelDiscovery();
        }

        // Unregister broadcast listeners
        this.unregisterReceiver(mReceiver);
    }


    //------------------------广播发现蓝牙设备------------------------
    /**
     * The BroadcastReceiver that listens for discovered devices and changes the title when
     * discovery is finished
     */
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    devicesList.add(new DevicesBean("found",device));
                    Log.i(TAG, "----find:  name:" + device.getName() + "   Address" + device.getAddress());
                }
                // When discovery is finished, change the Activity title
                showDevices();
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                setProgressBarIndeterminateVisibility(false);
            }
        }
    };

    public void showDevices(){
        if (myAdapter==null){
            myAdapter = new DevicesAdapter(BluetoothDemoActivity.this,devicesList);
            devicesLv.setAdapter(myAdapter);
        }else{
            myAdapter.resetData(devicesList);
        }
    }




    public void myClient(View view){

    }





    private ConnectedThread  connectedThread;
    /**
     * @author qiwenming
     * @date 2016/5/7 22:11
     * @ClassName: BluetoothDemoActivity
     * @Description  连接操作
     */
    private class AcceptThread extends Thread {
        private final BluetoothServerSocket mmServerSocket;

        public AcceptThread() {
            // Use a temporary object that is later assigned to mmServerSocket,
            // because mmServerSocket is final
            BluetoothServerSocket tmp = null;
            try {
                // MY_UUID is the app's UUID string, also used by the client code
//                tmp = mBtAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
                tmp = mBtAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID_SECURE);
            } catch (IOException e) { }
            mmServerSocket = tmp;
        }

        public void run() {
            BluetoothSocket socket = null;
            // Keep listening until exception occurs or a socket is returned
            while (true) {
                try {
                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    break;
                }
                // If a connection was accepted
                if (socket != null) {
                    // Do work to manage the connection (in a separate thread)
//                    manageConnectedSocket(socket);
                    try {
                        mmServerSocket.close();
                        if(connectedThread==null){
                            connectedThread  = new ConnectedThread(socket);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        /** Will cancel the listening socket, and cause the thread to finish */
        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) { }
        }
    }


    /**
     * @author qiwenming
     * @date 2016/5/7 22:22
     * @ClassName: BluetoothDemoActivity
     * @Description:  已经连接
     */
    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);
                    Log.i(TAG, "run: "+new String(buffer,0,bytes));
                } catch (IOException e) {
                    break;
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) { }
        }

        /* Call this from the main activity to shutdown the connection */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }




    /**
     * This thread runs while attempting to make an outgoing connection
     * with a device. It runs straight through; the connection either
     * succeeds or fails.
     */
    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
        private String mSocketType;

        public ConnectThread(BluetoothDevice device, boolean secure) {
            mmDevice = device;
            BluetoothSocket tmp = null;
            mSocketType = secure ? "Secure" : "Insecure";

            // Get a BluetoothSocket for a connection with the
            // given BluetoothDevice
            try {
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID_SECURE);
//                if (secure) {
//                    tmp = device.createRfcommSocketToServiceRecord(MY_UUID_SECURE);
//                } else {
//                    tmp = device.createInsecureRfcommSocketToServiceRecord( MY_UUID_INSECURE);
//                }
            } catch (IOException e) {
                Log.e(TAG, "Socket Type: " + mSocketType + "create() failed", e);
            }
            mmSocket = tmp;
        }

        public void run() {
            Log.i(TAG, "BEGIN mConnectThread SocketType:" + mSocketType);
            setName("ConnectThread" + mSocketType);

            // Always cancel discovery because it will slow down a connection
            mBtAdapter.cancelDiscovery();

            // Make a connection to the BluetoothSocket
            try {
                // This is a blocking call and will only return on a
                // successful connection or an exception
                mmSocket.connect();
                OutputStream os = mmSocket.getOutputStream();
                os.write("xiaoming".getBytes());
                os.flush();
            } catch (IOException e) {
                // Close the socket
                try {
                    mmSocket.close();
                } catch (IOException e2) {
                    Log.e(TAG, "unable to close() " + mSocketType +
                            " socket during connection failure", e2);
                }
//                connectionFailed();
                return;
            }

//            // Reset the ConnectThread because we're done
//            synchronized (BluetoothChatService.this) {
//                mConnectThread = null;
//            }

            // Start the connected thread
//            connected(mmSocket, mmDevice, mSocketType);
//            mConnectedThread = new ConnectedThread(socket, socketType);
//            mConnectedThread.start();
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "close() of connect " + mSocketType + " socket failed", e);
            }
        }
    }


}
