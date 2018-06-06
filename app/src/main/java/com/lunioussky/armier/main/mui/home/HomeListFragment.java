package com.lunioussky.armier.main.mui.home;

import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.databinding.HomeTabBind;

/**
 * Created by 11645 on 2018/3/14.
 */

public class HomeListFragment extends BaseFragment<HomeTabBind> {


    @Override
    public int setContent() {
        return R.layout.fragment_home_tab_list;
    }


    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    public static HomeListFragment getInstance(String title) {
        HomeListFragment sf = new HomeListFragment();
        return sf;
    }
}
