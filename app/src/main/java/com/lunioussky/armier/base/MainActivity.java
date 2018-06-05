package com.lunioussky.armier.base;


/**
 * Created by 11645 on 2018/3/13.
 */

public class MainActivity extends BaseActivity {

    @Override
    public BaseFragment setRootFragment() {
        setSwipeBackEnable(false);
        return new MainFragment();
    }


}
