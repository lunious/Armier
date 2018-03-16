package com.lunioussky.armier.main;


import com.lunioussky.armier.base.BaseActivity;
import com.lunioussky.armier.base.BaseFragment;


/**
 * Created by 11645 on 2018/3/13.
 */

public class MainActivity extends BaseActivity {

    @Override
    public BaseFragment setRootFragment() {
        return new MainFragment();
    }


}
