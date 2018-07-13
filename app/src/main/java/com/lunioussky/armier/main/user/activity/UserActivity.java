package com.lunioussky.armier.main.user.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lunioussky.armier.base.BaseTabActivity;
import com.lunioussky.armier.base.BaseTabFragment;
import com.lunioussky.armier.main.user.fragment.UserFragment;

/**
 * Author: lunious
 * Date: 2018/6/8 16:51
 * Description:
 */
@Route(path = "/com/UserActivity")
public class UserActivity extends BaseTabActivity {
    @Override
    public BaseTabFragment setRootFragment() {
        return new UserFragment();
    }
}
