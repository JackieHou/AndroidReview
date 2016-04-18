package com.qwm.androidreview.xmljsondemo;

import java.io.Serializable;

/**
 * @author qiwenming
 * @date 2016/4/18 20:46
 * @ClassName: TestBean
 * @Description:  测试使用的javaBean
 */
public class TestBean implements Serializable {

    private int age;
    private String phoneNum;
    private String name;

    public TestBean() {
    }

    public TestBean(int age, String phoneNum, String name) {
        this.age = age;
        this.phoneNum = phoneNum;
        this.name = name;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "age=" + age +
                ", phoneNum='" + phoneNum + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
