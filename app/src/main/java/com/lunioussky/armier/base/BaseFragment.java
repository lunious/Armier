package com.lunioussky.armier.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.zhy.changeskin.SkinManager;

import me.yokeyword.fragmentation.SupportFragment;


/**
 * Created by 11645 on 2018/3/15.
 */

public class BaseFragment extends SupportFragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //换肤功能页面注册
        SkinManager.getInstance().register(getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //换肤功能注销
        SkinManager.getInstance().unregister(getActivity());
    }

}
