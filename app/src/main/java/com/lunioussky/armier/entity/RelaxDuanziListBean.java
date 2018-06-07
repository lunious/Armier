package com.lunioussky.armier.entity;

import java.io.Serializable;

/**
 * Author: lunious
 * Date: 2018/6/6 23:25
 * Description:
 */
public class RelaxDuanziListBean implements Serializable {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "RelaxDuanziListBean{" +
                "text='" + text + '\'' +
                '}';
    }
}
