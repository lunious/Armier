package com.lunioussky.armier.main.oui.lucky;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lunioussky.armier.base.BaseActivity;
import com.lunioussky.armier.base.BaseFragment;

/**
 * Author: lunious
 * Date: 2018/6/3 13:35
 * Description:
 */
@Route(path = "/com/LuckyActivity")
public class LuckyActivity extends BaseActivity {
    @Override
    public BaseFragment setRootFragment() {
        return new LuckyFragment();
    }
}
