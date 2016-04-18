package com.qwm.androidreview.filestoragedemo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.qwm.androidreview.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qiwenming
 * @date 2016/4/18 13:27
 * @ClassName: FileStorageActivity
 * @Description:  数据存储 5种
 */
public class FileStorageActivity extends AppCompatActivity {

    private String TAG = FileStorageActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_storage);
    }

    /**
     * 测试
     * @param view
     */
    public void test(View view){
//        fileTest();
//        testNet();
        testSharedPreferences();
    }

    /**
     * 文件测试
     */
    public void fileTest(){
        //-------------------------内部存储------------------------
        //缓存
        File cacheDir = getCacheDir();
        Log.i(TAG, "fileTest: getCacheDir:"+cacheDir.getAbsolutePath());

        File dataDir = getFilesDir();
        Log.i(TAG, "fileTest: getFilesDir:"+dataDir.getAbsolutePath());

        File exCacheDir = getExternalCacheDir();
        Log.i(TAG, "fileTest: getExternalCacheDir:"+exCacheDir.getAbsolutePath());

        try {
            FileOutputStream fos = openFileOutput("xm.jj",MODE_PRIVATE);
            fos.write("我是小明".getBytes());
            fos.close();

            dataDir = getFilesDir();
            File[] files = dataDir.listFiles();
            for (int i = 0; i < files.length; i++) {
                Log.i(TAG, "fileTest: getFilesDir:"+files[i].getPath());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //----------------------外部存储--------------------
//        if(Environment.getExternalStorageState()==Environment.MEDIA_MOUNTED){//有sd卡
            File exDataDir = Environment.getDataDirectory();
            File exDownloadDir =   Environment.getDownloadCacheDirectory();
            File exStorageDir =  Environment.getExternalStorageDirectory();
            File exPublicDir =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
            File exRootDir = Environment.getRootDirectory();
            Log.i(TAG, "-----------------------------------------");
            Log.i(TAG, "fileTest: getDataDirectory--"+exDataDir.getAbsolutePath());
            Log.i(TAG, "fileTest: getDownloadCacheDirectory--"+exDownloadDir.getAbsolutePath());
            Log.i(TAG, "fileTest: getExternalStorageDirectory--"+exStorageDir.getAbsolutePath());
            Log.i(TAG, "fileTest: getExternalStoragePublicDirectory DIRECTORY_MUSIC--"+exPublicDir.getAbsolutePath());
            Log.i(TAG, "fileTest: getRootDirectory--"+exRootDir.getAbsolutePath());
//        }
    }

    /**
     * 网络的存储方式
     */
    public void testNet(){
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL("http://www.weather.com.cn/data/sk/101010100.html");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn .setRequestProperty("Accept-Encoding", "identity");
//                    conn.setConnectTimeout(5*1000);
                    if(conn.getResponseCode()==200){
                        String msg = conn.getResponseMessage();
                        Log.i(TAG, "testNet: getResponseMessage  "+msg);
                        Log.i(TAG, "testNet: getResponseMessage  "+conn.getContentLength());
                        String content =  conn.getContent().toString();


                        InputStream is = conn.getInputStream();
//                        InputStreamReader reader = new InputStreamReader(is);
//                        BufferedReader bufferReader = new BufferedReader(reader);
//                        StringBuilder sb = new StringBuilder();
//                        char[] buffer = new char[1024];
//                        int len =0;
////                        str=bufferReader.readLine();
//                        while ((len=bufferReader.read(buffer))>0){
//                            sb.append(new String(buffer,0,len));
//                        }
//                        Log.i(TAG, "testNet: data:-----》 "+sb.toString());

                        byte[] buffer = new byte[1024];
                        int len =0;
                        StringBuilder sb = new StringBuilder();
                        while((len = is.read(buffer))>0){
                            sb.append(new String(buffer,0,len));
                        }

                        Log.i(TAG, "testNet: data:-----》 "+sb.toString());
                    }
//                    conn.disconnect();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();


    }

    /**
     * SharedPreferences
     */
    public void testSharedPreferences(){
       SharedPreferences sp  = getSharedPreferences("xm",MODE_APPEND);
        sp.edit().putInt("age",38).commit();
        sp.edit().putString("name","qwm").commit();
        int age = sp.getInt("age",9);
        String name = sp.getString("name","xm");
        Toast.makeText(FileStorageActivity.this, age+"---"+name, Toast.LENGTH_SHORT).show();
    }


    //----------------------数据库的操作-------------------------

    MySQLiteOpenHelper sqLiteOpenHelper = null;
    SQLiteDatabase db = null;

    public void createDb(){
        if(db==null){
            sqLiteOpenHelper = new MySQLiteOpenHelper(this,"testxm",null,1);
            db = sqLiteOpenHelper.getWritableDatabase();
        }
    }

    /**
     * 增
     * @param view
     */
    public void testInsert(View view){
        createDb();
        db.execSQL("insert into person(age,name) values(23,'xm')");
        db.execSQL("insert into person(age,name) values(?,?)",new Object[]{56,"xm_"+System.currentTimeMillis()/100000000});

        ContentValues cv = new ContentValues();
        cv.put("age",98);
        cv.put("name","insert_"+System.currentTimeMillis()/100000000);
        db.insert("person",null,cv);
    }

    /**
     * 删
     * @param view
     */
    public void testDelete(View view){
        createDb();
        db.delete("person","age = 56",null);
    }

    /**
     * 改
     * @param view
     */
    public void testUpdate(View view){
        createDb();
        ContentValues cv = new ContentValues();
        cv.put("age",103);
        cv.put("name","update_"+System.currentTimeMillis()/100000000);
        db.update("person",cv,"age=23",null);
    }

    /**
     * 查
     * @param view
     */
    public void testQuery(View view){
        createDb();
        Cursor cs = db.query("person",null,null,null,null,null,null);
        while(cs.moveToNext()){
           int age = cs.getInt(cs.getColumnIndex("age"));
            String name = cs.getString(cs.getColumnIndex("name"));
            Log.i(TAG, "testQuery:    name:"+name+"   age"+age);
        }
    }

    //----------------------内容提供者的操作-------------------------
    /**
     * 增
     * @param view
     */
    public void testContentInsert(View view){
        Log.i(TAG, "testContentInsert: ");
        ContentResolver cr = getContentResolver();
        ContentValues cv = new ContentValues();
        cv.put("age",345);
        cv.put("name","content_i_"+System.currentTimeMillis()/100000000);
        cr.insert(Uri.parse("content://com.qwm.storage/person"),cv);
    }

    /**
     * 删
     * @param view
     */
    public void testContentDelete(View view){
        Log.i(TAG, "testtContentDelete: ");
        ContentResolver cr = getContentResolver();
        cr.delete(Uri.parse("content://com.qwm.storage/person"),"age=345",null);
    }

    /**
     * 改
     * @param view
     */
    public void testContentUpdate(View view){
        Log.i(TAG, "testtContentUpdate: ");
        ContentResolver cr = getContentResolver();
        ContentValues cv = new ContentValues();
        cv.put("age",345);
        cv.put("name","content_up_"+System.currentTimeMillis()/100000000);
        cr.update(Uri.parse("content://com.qwm.storage/person"),cv,null,null);
    }

    /**
     * 查
     * @param view
     */
    public void testContentQuery(View view){
        Log.i(TAG, "testtContentQuery: ");
        ContentResolver cr = getContentResolver();
        Cursor cs =  cr.query(Uri.parse("content://com.qwm.storage/person"),null,null,null,null);;
        while(cs.moveToNext()){
            int age = cs.getInt(cs.getColumnIndex("age"));
            String name = cs.getString(cs.getColumnIndex("name"));
            Log.i(TAG, "testtContentQuery:    name:"+name+"   age"+age);
        }
    }
}
