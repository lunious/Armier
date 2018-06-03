package com.lunioussky.armier.ui.news;



import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lunioussky.armier.R;
import com.lunioussky.armier.main.BaseFragment;
import com.lunioussky.armier.databinding.NewsFragmentBind;


/**
 * Created by 11645 on 2018/3/13.
 */

public class NewsFragment extends BaseFragment {

    private NewsFragmentBind bind;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater,R.layout.fragment_news,container,false);
        return bind.getRoot();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
