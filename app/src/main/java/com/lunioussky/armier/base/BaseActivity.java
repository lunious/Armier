package com.lunioussky.armier.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.lunioussky.armier.R;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;


/**
 * Author: lunious
 * Date: 2018/6/2 21:42
 * Description: activity的基类，一些公用逻辑可在此处理
 */

public abstract class BaseActivity extends SwipeBackActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化，默认透明状态栏和黑色导航栏
        ImmersionBar.with(this).init();
        initContainer(savedInstanceState);
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
        //不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
        ImmersionBar.with(this).destroy();
        //垃圾回收
        System.gc();
        System.runFinalization();
        super.onDestroy();

    }


    @Override
    public void onBackPressedSupport() {
        // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport();
    }


}
