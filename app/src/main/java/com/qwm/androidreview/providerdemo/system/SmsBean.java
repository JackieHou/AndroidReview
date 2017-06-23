package com.qwm.androidreview.providerdemo.system;

import java.io.Serializable;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/6/22<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class SmsBean implements Serializable {
    public String address ;
    public String body ;
    public long date;
    public int type;//1接收，2发送

    public SmsBean(String address, String body, long date, int type) {
        this.address = address;
        this.body = body;
        this.date = date;
        this.type = type;
    }

    public SmsBean() {
    }

    @Override
    public String toString() {
        return "SmsBean{" +
                "address='" + address + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                ", type=" + type +
                '}';
    }
}
