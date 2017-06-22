package com.qwm.androidreview.datastoragedemo.externalstorage;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hp.hpl.sparta.Text;
import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.Date;

public class ExternalStorageActivity extends BaseActivity {
    private static final String TAG = "ExternalStorageActivity";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
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
        sb.append("\ngetExternalFilesDir : "+getExternalFilesDir(Environment.DIRECTORY_ALARMS).getAbsolutePath());
        sb.append("\ngetExternalFilesDir : "+getExternalFilesDir(Environment.DIRECTORY_DCIM).getAbsolutePath());
        sb.append("\ngetExternalFilesDir : "+getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath());
        sb.append("\ngetExternalFilesDir : "+getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        sb.append("\ngetExternalFilesDir : "+getExternalFilesDir(Environment.DIRECTORY_MOVIES).getAbsolutePath());
        sb.append("\ngetExternalFilesDir : "+getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath());
        sb.append("\ngetExternalFilesDir : "+getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS).getAbsolutePath());
        sb.append("\ngetExternalFilesDir : "+getExternalFilesDir(Environment.DIRECTORY_PODCASTS).getAbsolutePath());
        sb.append("\ngetExternalFilesDir : "+getExternalFilesDir(Environment.DIRECTORY_RINGTONES).getAbsolutePath());
        sb.append("\ngetFilesDir : "+getExternalMediaDirs()[0].getAbsolutePath());
        sb.append("\ngetObbDir : "+getObbDir().getAbsolutePath());
        sb.append("\nEnvironment.getDataDirectory() : "+Environment.getDataDirectory().getAbsolutePath());
        sb.append("\nEnvironment.getExternalStorageDirectory() : "+Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append("\nEnvironment.getExternalStoragePublicDirectory : "+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS).getAbsolutePath());
        sb.append("\nEnvironment.getExternalStoragePublicDirectory : "+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());
        sb.append("\nEnvironment.getExternalStoragePublicDirectory : "+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath());
        sb.append("\nEnvironment.getExternalStoragePublicDirectory : "+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        sb.append("\nEnvironment.getExternalStoragePublicDirectory : "+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath());
        sb.append("\nEnvironment.getExternalStoragePublicDirectory : "+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath());
        sb.append("\nEnvironment.getExternalStoragePublicDirectory : "+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS).getAbsolutePath());
        sb.append("\nEnvironment.getExternalStoragePublicDirectory : "+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS).getAbsolutePath());
        sb.append("\nEnvironment.getExternalStoragePublicDirectory : "+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES).getAbsolutePath());
        sb.append("\nEnvironment.getRootDirectory() : "+Environment.getRootDirectory().getAbsolutePath());
        textView.setText(sb.toString());
        Log.i(TAG, sb.toString());
    }

    int i = 0;
    public void onStorage(View view){
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/test_"+i;
        i = ++i%10;
        Log.i(TAG, "onStorage: "+path);
        File file = new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        try {
            FileWriter fw = new FileWriter(file,true);
            fw.write("\n现在是："+(new Date()).toString());
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onGet(View view){
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/test_"+i;
        i = ++i%10;
        File file = new File(path);
//        if(!file.exists())
//            return;
        try {
            FileReader fr = new FileReader(file);
            StringBuilder sb = new StringBuilder();
            sb.append("==========="+file.getName()+"====================");
            char buf[] = new char[1024];
            int len = 0;
            while( (len=fr.read(buf) )>0){
                sb.append(new String(buf,0,len));
            }
            textView.setText(sb.toString());
            Log.i(TAG, sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
