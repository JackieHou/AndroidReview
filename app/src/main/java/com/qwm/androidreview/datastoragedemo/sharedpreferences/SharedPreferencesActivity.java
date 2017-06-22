package com.qwm.androidreview.datastoragedemo.sharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;

import java.util.HashSet;
import java.util.Set;

/**
 * <b>Project:</b> YuantaiApplication<br>
 * <b>Create Date:</b> 2017/6/16<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class SharedPreferencesActivity extends BaseActivity {
    private static final String TAG = "sha";
    SharedPreferences sharedP;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        textView = (TextView)findViewById(R.id.textview);
        sharedP = getSharedPreferences("SharedPreferencesActivity",0);
    }

    public void onStorage(View view){
        Editor editor = sharedP.edit();
        editor.putInt("Int",10);
        editor.putBoolean("Boolean",true);
        editor.putString("String","testqwm");
        editor.putFloat("Float",23.5f);
        editor.putLong("Long",System.currentTimeMillis());

        Set<String> stringSet = new HashSet<String>();
        stringSet.add("qwm__1");
        stringSet.add("qwm__2");
        stringSet.add("qwm__3");
        stringSet.add("qwm__4");
        editor.putStringSet("StringSet",stringSet);
        editor.commit();

        Toast.makeText(SharedPreferencesActivity.this, "存入成功", Toast.LENGTH_SHORT).show();
    }

    public void onGet(View view){
        StringBuilder  sb = new StringBuilder();
        sb.append("Int:"+sharedP.getInt("Int",0));
        sb.append("\nBoolean:"+sharedP.getBoolean("Boolean",false));
        sb.append("\nString:"+sharedP.getString("String",""));
        sb.append("\nFloat:"+sharedP.getFloat("Float",0));
        sb.append("\nLong:"+sharedP.getLong("Long",0));
        Set<String> stringSet = sharedP.getStringSet("StringSet",null);
        if(stringSet==null || stringSet.size()<=0) {
            sb.append("\nStringSet:null");
        }else{
            sb.append("\nStringSet\n    [\n");
            for (String str : stringSet){
                sb.append("            "+str+"\n");
            }
            sb.append("    ]");
        }
        textView.setText(sb.toString());
    }
}
