package com.qwm.androidreview.listazdemo;

import java.io.Serializable;

/**
 * @author qiwenming
 * @date 2016/4/21 16:35
 * @ClassName: AZBean
 * @Description:  数据类
 */
public class AZBean implements Serializable{

    public AZBean(String letter, String content) {
        this.letter = letter;
        this.content = content;
    }

    public AZBean(String content) {
        this.content = content;
        this.letter = PinyinUtils.getFirstSpellOne(content).toUpperCase();
    }

    public String letter;
    public String content;

}
