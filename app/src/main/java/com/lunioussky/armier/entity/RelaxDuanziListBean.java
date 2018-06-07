package com.lunioussky.armier.entity;

import java.io.Serializable;

/**
 * Author: lunious
 * Date: 2018/6/6 23:25
 * Description:
 */
public class RelaxDuanziListBean implements Serializable {

    private String comment_author;
    private String comment_date;
    private String text_content;

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

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }

    @Override
    public String toString() {
        return "RelaxDuanziListBean{" +
                "comment_author='" + comment_author + '\'' +
                ", comment_date='" + comment_date + '\'' +
                ", text_content='" + text_content + '\'' +
                '}';
    }
}
