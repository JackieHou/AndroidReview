package com.qwm.androidreview.datastoragedemo.sqlitestorage;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/6/22<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class PersonBean {
    public String name;
    public int age;

    public PersonBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public PersonBean() {
    }

    @Override
    public String toString() {
        return "PersonBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
