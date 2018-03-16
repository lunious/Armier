package com.lunioussky.armier.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseFragment;

/**
 * Created by 11645 on 2018/3/14.
 */

public class ResultListFragment extends BaseFragment {


    private String mTitle = null;

    private TextView tvTab = null;


    @Override
    public Object setLayout() {
        return R.layout.fragment_home_tab;
    }

    @Override
    public void initView() {
        tvTab = getView().findViewById(R.id.tv_tab);
    }

    @Override
    public void initData() {
        tvTab.setText(mTitle);
    }

    @Override
    public void initEvent() {

    }


    public static ResultListFragment getInstance(String title) {
        ResultListFragment sf = new ResultListFragment();
        sf.mTitle = title;
        return sf;
    }
}
