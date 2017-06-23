package com.qwm.androidreview.providerdemo.myself;

import java.io.Serializable;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/6/23<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class PersonBean implements Serializable {
    public int pid;
    public String name;
    public int age;

    public PersonBean(int pid, String name, int age) {
        this.pid = pid;
        this.name = name;
        this.age = age;
    }

    public PersonBean() {
    }

    @Override
    public String toString() {
        return "PersonBean{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
