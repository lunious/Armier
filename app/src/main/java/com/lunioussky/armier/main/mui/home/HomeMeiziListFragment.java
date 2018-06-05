package com.lunioussky.armier.main.mui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.databinding.HomeMeiziListBind;

/**
 * Author: lunious
 * Date: 2018/6/5 15:46
 * Description:
 */
public class HomeMeiziListFragment extends BaseFragment {

    HomeMeiziListBind bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_home_meizi_list, container, false);
        return bind.getRoot();
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
