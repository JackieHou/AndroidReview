package com.qwm.androidreview.dessignmodedemo;

import android.graphics.AvoidXfermode;

/**
 * @author qiwenming
 * @date 2016/4/19 13:31
 * @ClassName: ModeTest
 * @Description:  设计模式  单例模式
 */
public class ModeTest {

//    //懒汉式
//    private ModeTest(){}
//    private static ModeTest instance;
//    public static ModeTest getInstance(){
//        if(instance==null){
//            instance = new ModeTest();
//        }
//        return instance;
//    }

    //饿汉式
    private ModeTest(){}
    private static ModeTest instance = new ModeTest();
    public static ModeTest getInstance(){
        return instance;
    }



}
