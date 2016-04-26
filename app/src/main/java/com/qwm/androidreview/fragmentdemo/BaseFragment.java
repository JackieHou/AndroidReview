package com.qwm.androidreview.fragmentdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qwm.androidreview.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BaseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 *
 * 这里的值返回回调其实很简单
 *
 * 1. A--->B
 *    如果A打开B以后，想要回到B关闭以后回传的数据，步骤如下
 *    1.1 A--->B 需要使用 openFragment(B,true),true表示后回调
 *    1.2 B中调用setResualt(bundle)方法，传入要发送的数据即可
 *    1.3 A 中重新 handleCall(bundle)处理回传数据
 *
 *  实现原理是
 *     1. 更具传入的值，判断要不要创建一个回传的监听，如果true,就创建一个监听对象
 *        if(isCall){//需要回调
               fragment.preCallListener = new MyCallListener();
          }
        //这个回调其实就是把值存起来，供onCreateView的方法；里里面判断要不要执行 handleCall方法
         public class MyCallListener{
             public void myResult(Bundle bundle){
                 resultBundle = bundle;
             }
         }
         //4 回调
         if(resultBundle!=null){
              handlerCall(resultBundle);
         }
      2.B中 调用setResualt方法设置值，把这值先保存起来，用于在 onDestory()的方法里面判断 要不要调用功用 A传过来的监听
       public void setResult(Bundle bundle){
          returnBundle = bundle;
       }

     @Override
    public void onDestroy() {
           super.onDestroy();
           //数据不为null,回调不为空，才给你返回数据
           if(returnBundle!=null && preCallListener!=null){//需要回到，那么说明
               preCallListener.myResult(returnBundle);
         }
    }

 *
 *
 */
public abstract class BaseFragment extends Fragment {

    /**
     * 保存 打开界面关闭时候，回到这个Fragment的数据，就是回调的数据的
     */
    private Bundle resultBundle;
    /**
     * 这个是上一个界面的回调，如果有这个说明需要回调
     */
    private MyCallListener preCallListener;

    /**
     * 保存需要放回的数，给用户提供一个方法，让其调用 setResualt(),设置放回的结果
     */
    private Bundle returnBundle ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // 1.获取传递过来的参数
        if (getArguments() != null) {
            getArgFromFrag(getArguments());
        }

        // 2.初始化
        initData();

        // 3.初始化View
        View v = initView(inflater, container, savedInstanceState);

        //4 回调
        if(resultBundle!=null){
            handlerCall(resultBundle);
        }

        return v;
    }


    /**
     * 参数的处理
     * @param arguments
     */
    public void getArgFromFrag(Bundle arguments){

    }

    public abstract void initData();

    public abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public void handlerCall(Bundle resultBundle){

    }

    /**
     * 打开Fragment
     * @param fragment
     * @param isCall 是否需要回调
     */
    public void openFragment(BaseFragment fragment, boolean isCall) {
        if(isCall){//需要回调
            fragment.preCallListener = new MyCallListener();
        }
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_content, fragment);
        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commit();
    }

    public void openFragment(BaseFragment fragment){
        openFragment(fragment,false);
    }


    public class MyCallListener{
        public void myResult(Bundle bundle){
            resultBundle = bundle;
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        //数据不为null,回调不为空，才给你返回数据
        if(returnBundle!=null && preCallListener!=null){//需要回到，那么说明
            preCallListener.myResult(returnBundle);
        }
    }

    /**
     * 关闭fragment
     */
    public void closeFragment() {
        if(getFragmentManager()!=null){
            getFragmentManager().popBackStack();
        }else{
            System.out.println("getFragmentManager()==null");
        }
    }

    /**
     * 提供给子类设置放回值的
     * @param bundle
     */
    public void setResult(Bundle bundle){
        returnBundle = bundle;
    }
}
