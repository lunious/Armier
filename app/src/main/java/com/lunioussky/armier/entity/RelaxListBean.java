package com.lunioussky.armier.entity;


import java.io.Serializable;

/**
 * Author: lunious
 * Date: 2018/7/13 11:57
 * Description:
 */
public class RelaxListBean implements Serializable {
    private String zTitle;
    private String zSubtitle;
    private String sSubImageLink;
    private String zDetailLink;
    private String zType;

    public String getzTitle() {
        return zTitle;
    }

    public void setzTitle(String zTitle) {
        this.zTitle = zTitle;
    }

    public String getzSubtitle() {
        return zSubtitle;
    }

    public void setzSubtitle(String zSubtitle) {
        this.zSubtitle = zSubtitle;
    }

    public String getsSubImageLink() {
        return sSubImageLink;
    }

    public void setsSubImageLink(String sSubImageLink) {
        this.sSubImageLink = sSubImageLink;
    }

    public String getzDetailLink() {
        return zDetailLink;
    }

    public void setzDetailLink(String zDetailLink) {
        this.zDetailLink = zDetailLink;
    }

    public String getzType() {
        return zType;
    }

    public void setzType(String zType) {
        this.zType = zType;
    }

    @Override
    public String toString() {
        return "RelaxListBean{" +
                "zTitle='" + zTitle + '\'' +
                ", zSubtitle='" + zSubtitle + '\'' +
                ", sSubImageLink='" + sSubImageLink + '\'' +
                ", zDetailLink='" + zDetailLink + '\'' +
                ", zType='" + zType + '\'' +
                '}';
    }
}
