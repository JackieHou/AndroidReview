package com.qwm.androidreview.fragmentdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qwm.androidreview.R;

import java.nio.BufferUnderflowException;

public class MyFragmentTwo extends BaseFragment {


    @Override
    public void initData() {

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_fragment_one, container, false);
        TextView contentTv = (TextView) view.findViewById(R.id.tv_text);
        contentTv.setText(getClass().getSimpleName());
        view.findViewById(R.id.btn_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("msg","qiwenmignshiwo");
                setResult(bundle);
                closeFragment();
            }
        });
        return view;
    }
}
