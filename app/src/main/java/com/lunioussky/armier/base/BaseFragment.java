package com.lunioussky.armier.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;


/**
 * Created by 11645 on 2018/3/15.
 */

public abstract class BaseFragment extends SwipeBackFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        }
        return rootView;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    public abstract Object setLayout();

    public abstract void initView();

    public abstract void initData();

    public abstract void initEvent();




}
