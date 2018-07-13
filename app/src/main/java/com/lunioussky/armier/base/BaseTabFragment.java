package com.lunioussky.armier.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;


/**
 * Author: lunious
 * Date: 2018/6/2 21:42
 * Description: fragment的基类，一些公用逻辑可在此处理
 */
public abstract class BaseTabFragment<BindingView extends ViewDataBinding> extends SwipeBackFragment {

    // 布局view
    protected BindingView bindingView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindingView = DataBindingUtil.inflate(inflater, setContent(), container, false);
        return bindingView.getRoot();
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        initData();
        initEvent();
    }


    /**
     * 布局
     */
    public abstract int setContent();

    /**
     * 数据
     */
    public abstract void initData();

    /**
     * 事件
     */
    public abstract void initEvent();

}
