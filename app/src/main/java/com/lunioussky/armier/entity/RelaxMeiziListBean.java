package com.lunioussky.armier.entity;

import java.io.Serializable;

/**
 * Author: lunious
 * Date: 2018/6/6 16:23
 * Description:
 */
public class RelaxMeiziListBean implements Serializable {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "RelaxMeiziListBean{" +
                "url='" + url + '\'' +
                '}';
    }
}
