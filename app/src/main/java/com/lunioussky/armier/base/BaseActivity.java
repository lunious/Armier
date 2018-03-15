package com.lunioussky.armier.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gyf.barlibrary.ImmersionBar;
import com.zhy.changeskin.SkinManager;

import me.yokeyword.fragmentation.SupportActivity;


/**
 * Created by 11645 on 2018/3/15.
 */

public class BaseActivity extends SupportActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //换肤功能页面注册
        SkinManager.getInstance().register(this);
        //初始化，默认透明状态栏和黑色导航栏
        ImmersionBar.with(this).init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //换肤功能注销
        SkinManager.getInstance().unregister(this);
        //不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
        ImmersionBar.with(this).destroy();
    }

}
