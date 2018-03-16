package com.lunioussky.armier.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.lunioussky.armier.R;
import com.zhy.changeskin.SkinManager;

import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;


/**
 * Created by 11645 on 2018/3/15.
 */

public abstract class BaseActivity extends SwipeBackActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
        //换肤功能页面注册
        SkinManager.getInstance().register(this);
        //初始化，默认透明状态栏和黑色导航栏
        ImmersionBar.with(this).init();
    }

    //用来容纳Fragment的容器
    private void initContainer(@Nullable Bundle savedInstanceState) {
        @SuppressLint("RestrictedApi") final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.fragment_container);
        setContentView(container);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fragment_container, setRootFragment());
        }
    }

    public abstract BaseFragment setRootFragment();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //换肤功能注销
        SkinManager.getInstance().unregister(this);
        //不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
        ImmersionBar.with(this).destroy();
        //垃圾回收
        System.gc();
        System.runFinalization();
    }

}