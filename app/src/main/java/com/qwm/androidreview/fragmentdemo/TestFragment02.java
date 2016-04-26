package com.qwm.androidreview.fragmentdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qwm.androidreview.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TestFragment02.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TestFragment02#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment02 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = TestFragment02.class.getName();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TestFragment02() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment01.
     */
    // TODO: Rename and change types and number of parameters
    public static TestFragment02 newInstance(String param1, String param2) {
        TestFragment02 fragment = new TestFragment02();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
        Log.i(TAG, "onAttach: ----------");
    }

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.i(TAG, "onCreate: -----------");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ------------");
        View view = inflater.inflate(R.layout.fragment_test_fragment01, container, false);
        TextView contentTv = (TextView)view.findViewById(R.id.tv_content);
        contentTv.setText(getClass().getSimpleName());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: ------");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: -------------");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ------------------");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ----------------");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ------------------------");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: --------------");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: -------------");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        Log.i(TAG, "onDetach: ----------------");
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }





    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
