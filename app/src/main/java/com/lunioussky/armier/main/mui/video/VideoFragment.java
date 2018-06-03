package com.lunioussky.armier.main.mui.video;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lunioussky.armier.R;
import com.lunioussky.armier.main.BaseFragment;
import com.lunioussky.armier.databinding.VideoFragmentBind;


/**
 * Created by 11645 on 2018/3/13.
 */

public class VideoFragment extends BaseFragment {

    private VideoFragmentBind bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_video, container, false);
        return bind.getRoot();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
