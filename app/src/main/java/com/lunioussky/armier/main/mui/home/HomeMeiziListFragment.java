package com.lunioussky.armier.main.mui.home;

import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.databinding.HomeMeiziListBind;

/**
 * Author: lunious
 * Date: 2018/6/5 15:46
 * Description:
 */
public class HomeMeiziListFragment extends BaseFragment<HomeMeiziListBind> {


    @Override
    public int setContent() {
        return R.layout.fragment_home_meizi_list;
    }

    public static HomeMeiziListFragment getInstance(String title) {
        HomeMeiziListFragment sf = new HomeMeiziListFragment();
        return sf;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
