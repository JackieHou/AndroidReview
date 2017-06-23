package com.qwm.androidreview.providerdemo.system;

import java.io.Serializable;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/6/23<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class ContactsBean implements Serializable {
    public String phone;
    public String name;
    public String email;

    @Override
    public String toString() {
        return "ContactsBean{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
