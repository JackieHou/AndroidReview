package com.qwm.androidreview.datastoragedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;
import com.qwm.androidreview.datastoragedemo.contentprovider.CententProviderStorageActivity;
import com.qwm.androidreview.datastoragedemo.externalstorage.ExternalStorageActivity;
import com.qwm.androidreview.datastoragedemo.internalstorage.InternalStorageActivity;
import com.qwm.androidreview.datastoragedemo.networkstorage.NetWorkStorageActivity;
import com.qwm.androidreview.datastoragedemo.sharedpreferences.SharedPreferencesActivity;
import com.qwm.androidreview.datastoragedemo.sqlitestorage.SqliteStorageActivity;

/**
 * <b>Project:</b> YuantaiApplication<br>
 * <b>Create Date:</b> 2017/6/16<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b>
 *  数据存储
 * <br>
 */
public class DataStorageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_storage);
    }

    public void onSharedPreferences(View view){
        startActivity("SharedPreferences", SharedPreferencesActivity.class);
    }


    public void onInternalStorage(View view){
        startActivity("Internal Storage(内部存储)", InternalStorageActivity.class);
    }

    public void onExternalStorage(View view){
        startActivity("External Storage(外部存储)", ExternalStorageActivity.class);
    }

    public void CententProviderStorage(View view){
        startActivity("External Storage(外部存储)", CententProviderStorageActivity.class);
    }

    public void onSQLite(View view){
        startActivity("SQLite", SqliteStorageActivity.class);
    }

    public void onNetWorkConnection(View view){
        startActivity("Net WorkConnection", NetWorkStorageActivity.class);
    }
}
