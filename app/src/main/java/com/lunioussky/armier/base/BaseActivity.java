package com.lunioussky.armier.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.barlibrary.ImmersionBar;

import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * Author: lunious
 * Date: 2018/7/13 16:19
 * Description:
 */
public abstract class BaseActivity<BindingView extends ViewDataBinding> extends SwipeBackActivity {

    // 布局view
    protected BindingView bindingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化，默认透明状态栏和黑色导航栏
        ImmersionBar.with(this).init();
        bindingView = DataBindingUtil.setContentView(this,setContent());

        initData();
        initEvent();

    }
    @Override
    protected void onDestroy() {
        //不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
        ImmersionBar.with(this).destroy();
        //垃圾回收
        System.gc();
        System.runFinalization();
        super.onDestroy();

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
