package com.lunioussky.armier.ui.mine;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.databinding.MineFragmentBind;


/**
 * Created by 11645 on 2018/3/13.
 */


public class MineFragment extends BaseFragment {

    public MineFragmentBind bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false);
        return bind.getRoot();
    }

    @Override
    public void initData() {
    }

    @Override
    public void initEvent() {
        bind.setMyHandler(new MyHandler());
    }

    public class MyHandler {
        public void onClickTo(View view) {
            ARouter.getInstance().build("/com/TestActivity").navigation();
        }
    }
}
