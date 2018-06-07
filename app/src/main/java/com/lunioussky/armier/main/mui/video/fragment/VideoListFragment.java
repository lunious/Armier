package com.lunioussky.armier.main.mui.video.fragment;

import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.databinding.videoListFragmentBind;


/**
 * Author: lunious
 * Date: 2018/6/7 20:58
 * Description:
 */
public class VideoListFragment extends BaseFragment<videoListFragmentBind> {


    public static VideoListFragment getInstance(String title) {
        VideoListFragment sf = new VideoListFragment();
        return sf;
    }

    @Override
    public int setContent() {
        return R.layout.fragment_video_list;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
