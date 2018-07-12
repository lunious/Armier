package com.lunioussky.armier.main.mui.index.fragment;

import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseFragment;


/**
 * Author: lunious
 * Date: 2018/7/12 16:55
 * Description:
 */
public class IndexListFragment extends BaseFragment {

    private String mTitle = null;

    public static IndexListFragment getInstance(String title) {
        IndexListFragment sf = new IndexListFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public int setContent() {
        return R.layout.fragment_index_list;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
