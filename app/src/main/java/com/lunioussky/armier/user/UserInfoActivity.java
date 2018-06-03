package com.lunioussky.armier.user;

import com.lunioussky.armier.main.BaseActivity;
import com.lunioussky.armier.main.BaseFragment;

/**
 * Author: lunious
 * Date: 2018/6/3 12:45
 * Description:
 */
public class UserInfoActivity extends BaseActivity {
    @Override
    public BaseFragment setRootFragment() {
        return new UserInfoFragment();
    }
}
