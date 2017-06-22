package com.qwm.androidreview.datastoragedemo.internalstorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;

public class InternalStorageActivity extends BaseActivity {

    private TextView textView;
    private static final String TAG = "InternalStorageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        textView = (TextView)findViewById(R.id.textview);
    }

    public void onStoragelocation(View view){
        StringBuilder sb = new StringBuilder();
        //内部的
        sb.append("getDir : "+getDir("test1",MODE_PRIVATE).getAbsolutePath());
        sb.append("\ngetCacheDir : "+getCacheDir().getAbsolutePath());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sb.append("\ngetDataDir : "+getDataDir().getAbsolutePath());
        }
        sb.append("\ngetFilesDir : "+getFilesDir().getAbsolutePath());
        sb.append("\ngetCodeCacheDir : "+getCodeCacheDir().getAbsolutePath());
        sb.append("\ngetNoBackupFilesDir : "+getNoBackupFilesDir().getAbsolutePath());
        sb.append("\n----------------------------------");
        //外部的
        sb.append("\ngetExternalCacheDir : "+getExternalCacheDir().getAbsolutePath());
        sb.append("\ngetExternalFilesDir : "+getExternalFilesDir(Environment.DIRECTORY_ALARMS));
        sb.append("\ngetExternalFilesDir : "+getExternalFilesDir(Environment.DIRECTORY_DCIM));
        sb.append("\ngetExternalFilesDir : "+getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS));
        sb.append("\ngetExternalFilesDir : "+getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS));
        sb.append("\ngetExternalFilesDir : "+getExternalFilesDir(Environment.DIRECTORY_MOVIES));
        sb.append("\ngetExternalFilesDir : "+getExternalFilesDir(Environment.DIRECTORY_MUSIC));
        sb.append("\ngetExternalFilesDir : "+getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS));
        sb.append("\ngetExternalFilesDir : "+getExternalFilesDir(Environment.DIRECTORY_PODCASTS));
        sb.append("\ngetExternalFilesDir : "+getExternalFilesDir(Environment.DIRECTORY_RINGTONES));
        sb.append("\ngetFilesDir : "+getExternalMediaDirs()[0].getAbsolutePath());
        sb.append("\ngetObbDir : "+getObbDir().getAbsolutePath());
        textView.setText(sb.toString());
        Log.i(TAG, sb.toString());
    }

    int i=0;
    public void onStorage(View view){
        String filename = "myfile_"+i;
        i = ++i%10;
        String string = "Hello world!"+System.currentTimeMillis();
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onGet(View view){
        String filename = "myfile_"+i;
        i = ++i%10;
        FileInputStream inputStream;
        try {
            inputStream = openFileInput(filename);
            byte[] buf = new byte[1024];
            int lenght = inputStream.read(buf);
            inputStream.close();
            Log.i(TAG, "onGet: "+new String(buf,0,lenght));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onfileList(View view){
        String[] files = fileList();
        if(files!=null){
            StringBuilder sb = new StringBuilder();
            for(String path:files){
                sb.append(path+"\n");
            }
            textView.setText(sb.toString());
            Log.i(TAG, sb.toString());
        }
    }

    public void ondeleteFile(View view){
        deleteFile("myfile_"+i);
        i = ++i%10;
    }
}
