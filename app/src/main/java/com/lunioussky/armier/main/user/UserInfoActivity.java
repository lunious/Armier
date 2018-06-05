package com.lunioussky.armier.main.user;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lunioussky.armier.base.BaseActivity;
import com.lunioussky.armier.base.BaseFragment;

/**
 * Author: lunious
 * Date: 2018/6/3 12:45
 * Description:
 */
@Route(path = "/com/UserInfoActivity")
public class UserInfoActivity extends BaseActivity {
    @Override
    public BaseFragment setRootFragment() {
        return new UserInfoFragment();
    }
}
