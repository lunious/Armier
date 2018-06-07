package com.lunioussky.armier.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author: lunious
 * Date: 2018/6/6 16:23
 * Description:
 */
public class RelaxFreshListBean implements Serializable {
    private String title;
    private String author;
    private String text_content;
    private String pics;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    @Override
    public String toString() {
        return "RelaxFreshListBean{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", text_content='" + text_content + '\'' +
                ", pics='" + pics + '\'' +
                '}';
    }
}
