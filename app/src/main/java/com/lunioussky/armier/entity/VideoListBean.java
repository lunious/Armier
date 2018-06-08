package com.lunioussky.armier.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author: lunious
 * Date: 2018/6/6 16:23
 * Description:
 */
public class VideoListBean implements Serializable {
    private String video_url;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    @Override
    public String toString() {
        return "VideoListBean{" +
                "video_url='" + video_url + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
