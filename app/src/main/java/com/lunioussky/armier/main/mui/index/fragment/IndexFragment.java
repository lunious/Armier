package com.lunioussky.armier.main.mui.index.fragment;


import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.databinding.IndexFragmentBind;


/**
 * Created by 11645 on 2018/3/13.
 */

public class IndexFragment extends BaseFragment<IndexFragmentBind> {


    @Override
    public int setContent() {
        return R.layout.fragment_index;
    }

    @Override
    public void initData() {
        bindingView.setHint("空空如也");
    }

    @Override
    public void initEvent() {

    }
}
