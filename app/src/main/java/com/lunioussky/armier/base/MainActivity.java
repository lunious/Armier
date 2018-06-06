package com.lunioussky.armier.base;


import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * Created by 11645 on 2018/3/13.
 */

@Route(path = "/com/MainActivity")
public class MainActivity extends BaseActivity {

    @Override
    public BaseFragment setRootFragment() {
        //关闭侧换返回
        setSwipeBackEnable(false);
        return new MainFragment();
    }

    //再按一次退出程序
    private static final long WAIT_TIME = 3000L;
    private long TOUCH_TIME = 0;

    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public void onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
        }
    }
}
