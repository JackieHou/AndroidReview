package com.qwm.androidreview.xmljsondemo;

import java.io.Serializable;
import java.util.List;

/**
 * @author qiwenming
 * @date 2016/4/18 20:50
 * @ClassName: TestArrayBean
 * @Description:  测试
 */
public class TestArrayBean implements Serializable {
    private String nid;
    private List<TestBean>  persons;

    public TestArrayBean() {
    }

    public TestArrayBean(String nid, List<TestBean> persons) {
        this.nid = nid;
        this.persons = persons;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public List<TestBean> getPersons() {
        return persons;
    }

    public void setPersons(List<TestBean> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "TestArrayBean{" +
                "nid='" + nid + '\'' +
                ", persons=" + persons +
                '}';
    }
}
