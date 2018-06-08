package com.lunioussky.armier.main.mui.video.adapter;


import android.support.annotation.Nullable;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lunioussky.armier.R;
import com.lunioussky.armier.entity.VideoListBean;
import com.lunioussky.armier.main.view.GlideApp;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import java.util.List;

/**
 * Author: lunious
 * Date: 2018/6/8 11:01
 * Description:
 */
public class VideoListAdapter extends BaseQuickAdapter<VideoListBean, BaseViewHolder> {

    public TxVideoPlayerController controller;
    public NiceVideoPlayer niceVideoPlayer;


    public VideoListAdapter(int layoutResId, @Nullable List<VideoListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoListBean item) {
        niceVideoPlayer = helper.itemView.findViewById(R.id.videoPlayer);
        controller = new TxVideoPlayerController(helper.itemView.getContext());
        // 将列表中的每个视频设置为默认16:9的比例
        ViewGroup.LayoutParams params = niceVideoPlayer.getLayoutParams();
        params.width = helper.itemView.getResources().getDisplayMetrics().widthPixels; // 宽度为屏幕宽度
        params.height = (int) (params.width * 9f / 16f);    // 高度为宽度的9/16
        niceVideoPlayer.setLayoutParams(params);
        niceVideoPlayer.setController(controller);
        GlideApp.with(helper.itemView.getContext())
                .load(item.getImage())
                .into(controller.imageView());

        niceVideoPlayer.setUp(item.getVideo_url(), null);

    }

}
