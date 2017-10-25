package com.qwm.androidreview.markdown;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;
import com.zzhoujay.markdown.MarkDown;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * <b>Project:</b> AndroidReview<br>
 * <b>Create Date:</b> 2017/10/24<br>
 * <b>Author:</b> q<br>
 * <b>Description:</b> <br>
 */
public class MarkDownActivity extends BaseActivity {
    private static final String TAG = "MarkDownActivity";
    @Bind(R.id.markdown_tv)
    TextView mMarkdownTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_markdown);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //显示
    }
}
