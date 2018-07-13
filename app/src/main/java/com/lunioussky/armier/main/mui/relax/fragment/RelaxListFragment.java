package com.lunioussky.armier.main.mui.relax.fragment;

import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.databinding.relaxListFragmentBind;

/**
 * Author: lunious
 * Date: 2018/7/13 10:26
 * Description:
 */
public class RelaxListFragment extends BaseFragment<relaxListFragmentBind> {

    private String mTitle = null;

    public static RelaxListFragment getInstance(String title) {
        RelaxListFragment sf = new RelaxListFragment();
        sf.mTitle = title;
        return sf;
    }


    @Override
    public int setContent() {
        return R.layout.fragment_relax_list;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
