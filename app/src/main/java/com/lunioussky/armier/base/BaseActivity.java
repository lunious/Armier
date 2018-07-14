package com.lunioussky.armier.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.lunioussky.armier.app.App.APP_NAME;
import static com.lunioussky.armier.app.App.isDebug;

/**
 * Author: lunious
 * Date: 2018/7/14 18:18
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化，默认透明状态栏和黑色导航栏
        ImmersionBar.with(this).init();
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        initData(savedInstanceState);
        initEvent(savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        //不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
        ImmersionBar.with(this).destroy();
        //垃圾回收
        System.gc();
        System.runFinalization();
        unbinder.unbind();
        super.onDestroy();

    }

    /**
     * 布局
     */
    protected abstract int getLayoutId();

    /**
     * 数据
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 事件
     */
    protected abstract void initEvent(Bundle savedInstanceState);

    /**
     * 控制是否显示调试信息
     */
    public void TLog(String msg) {
        if (isDebug) {
            Log.d(APP_NAME, msg);
        }
    }

    /**
     * 隐藏软件盘
     */
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }


    /**
     * 显示软键盘
     */
    public void showInputMethod(){
        if (getCurrentFocus() != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInputFromInputMethod(getCurrentFocus().getWindowToken(),0);
        }
    }

    /**
     * 防止快速点击
     */
    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }
}
