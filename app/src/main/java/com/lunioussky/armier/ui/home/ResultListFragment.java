package com.lunioussky.armier.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lunioussky.armier.R;
import com.lunioussky.armier.main.BaseFragment;
import com.lunioussky.armier.databinding.HomeTabBind;

/**
 * Created by 11645 on 2018/3/14.
 */

public class ResultListFragment extends BaseFragment {


    private HomeTabBind bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater,R.layout.fragment_home_tab,container,false);
        return bind.getRoot();
    }


    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    public static ResultListFragment getInstance(String title) {
        ResultListFragment sf = new ResultListFragment();
        return sf;
    }
}
