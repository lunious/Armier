package com.lunioussky.armier.main.mui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.databinding.HomeTabBind;

/**
 * Created by 11645 on 2018/3/14.
 */

public class HomeListFragment extends BaseFragment {


    private HomeTabBind bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater,R.layout.fragment_home_tab_list,container,false);
        return bind.getRoot();
    }


    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    public static HomeListFragment getInstance(String title) {
        HomeListFragment sf = new HomeListFragment();
        return sf;
    }
}
