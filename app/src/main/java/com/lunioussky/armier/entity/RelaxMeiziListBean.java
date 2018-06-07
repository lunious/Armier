package com.lunioussky.armier.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author: lunious
 * Date: 2018/6/6 16:23
 * Description:
 */
public class RelaxMeiziListBean implements Serializable {
    private String comment_author;
    private String comment_date;
    private List<String> pics;

    public String getComment_author() {
        return comment_author;
    }

    public void setComment_author(String comment_author) {
        this.comment_author = comment_author;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    @Override
    public String toString() {
        return "RelaxMeiziListBean{" +
                "comment_author='" + comment_author + '\'' +
                ", comment_date='" + comment_date + '\'' +
                ", pics=" + pics +
                '}';
    }
}
