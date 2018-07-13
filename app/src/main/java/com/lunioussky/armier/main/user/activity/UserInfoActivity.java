package com.lunioussky.armier.main.user.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lunioussky.armier.base.BaseTabActivity;
import com.lunioussky.armier.base.BaseTabFragment;
import com.lunioussky.armier.main.user.fragment.UserInfoFragment;

/**
 * Author: lunious
 * Date: 2018/6/3 12:45
 * Description:
 */
@Route(path = "/com/UserInfoActivity")
public class UserInfoActivity extends BaseTabActivity {
    @Override
    public BaseTabFragment setRootFragment() {
        return new UserInfoFragment();
    }
}
