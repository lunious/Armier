package com.lunioussky.armier.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author: lunious
 * Date: 2018/6/6 16:23
 * Description:
 */
public class VideoListBean implements Serializable {
    private String title;
    private long length;
    private String imageUrl;
    private String videoUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }


    @Override
    public String toString() {
        return "VideoListBean{" +
                "title='" + title + '\'' +
                ", length=" + length +
                ", imageUrl='" + imageUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }
}
