package com.qwm.androidreview.fragmentdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qwm.androidreview.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TestTwoFragment001.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TestTwoFragment001#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestTwoFragment001 extends Fragment {
    private TextView contentTv;
    private String TAG = TestTwoFragment001.class.getName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_test_two_fragment001, container, false);
        contentTv = (TextView)view.findViewById(R.id.tv_text);
        contentTv.setText(getClass().getSimpleName());
        view.findViewById(R.id.btn_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test(view);
            }
        });
        return view;
    }


    public void test(View view){

        MyListener listener = new MyListener() {
            @Override
            void getResut(Bundle bundle) {
                String str = bundle.getString("test");
                Log.i(TAG, "getResut: "+str);
                contentTv.setText(str);
            }
        };

        TestTwoFragment002 tf1 = new TestTwoFragment002(listener);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack("dsafhdhis");
        ft.replace(R.id.fl_content,tf1,TestTwoFragment002.class.getSimpleName());
        ft.commit();
    }


    public abstract class MyListener {
        abstract void getResut(Bundle bundle);
    }

}
