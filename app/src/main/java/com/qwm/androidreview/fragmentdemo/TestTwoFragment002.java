package com.qwm.androidreview.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qwm.androidreview.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TestTwoFragment002.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TestTwoFragment002#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestTwoFragment002 extends Fragment {

    private TestTwoFragment001.MyListener listent;
    private TextView contentTv;

    public TestTwoFragment002() {
        listent = null;
    }

    public TestTwoFragment002(Bundle bundle) {
        this.setArguments(bundle);
        listent = null;
    }

    public TestTwoFragment002(TestTwoFragment001.MyListener listener) {
        this.listent = listener;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_two_fragment001, container, false);
        contentTv = (TextView)view.findViewById(R.id.tv_text);
        contentTv.setText(getClass().getSimpleName());
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Bundle bundle = new Bundle();
        bundle.putString("test","teshidehua");
        listent.getResut(bundle);
    }
}
