package com.lunioussky.armier.base;


import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

/**
 * Created by 11645 on 2018/3/13.
 */

@Route(path = "/com/MainActivity")
public class MainActivity extends BaseTabActivity {

    @Override
    public BaseTabFragment setRootFragment() {
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
        // 在全屏或者小窗口时按返回键要先退出全屏或小窗口，
        // 所以在Activity中onBackPress要交给NiceVideoPlayer先处理。
        if (NiceVideoPlayerManager.instance().onBackPressd()) return;

        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
        }
    }
}
