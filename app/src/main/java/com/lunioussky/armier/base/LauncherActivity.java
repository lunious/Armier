package com.lunioussky.armier.base;

/**
 * Author: lunious
 * Date: 2018/6/6 9:38
 * Description:
 */
public class LauncherActivity extends BaseTabActivity {
    @Override
    public BaseTabFragment setRootFragment() {
        //关闭侧换返回
        setSwipeBackEnable(false);
        return new LauncherFragment();
    }

}
