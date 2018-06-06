package com.lunioussky.armier.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;


/**
 * Author: lunious
 * Date: 2018/6/2 21:42
 * Description:
 */
public abstract class BaseFragment extends SwipeBackFragment {


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        initData();
        initEvent();
    }

    public abstract void initData();

    public abstract void initEvent();


}
