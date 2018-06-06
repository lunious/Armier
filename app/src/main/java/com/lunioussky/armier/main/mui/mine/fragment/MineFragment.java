package com.lunioussky.armier.main.mui.mine.fragment;

import android.view.View;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lunioussky.armier.R;
import com.lunioussky.armier.databinding.MineFragmentBind;
import com.lunioussky.armier.base.BaseFragment;


/**
 * Created by 11645 on 2018/3/13.
 */


public class MineFragment extends BaseFragment<MineFragmentBind> {


    @Override
    public int setContent() {
        return R.layout.fragment_mine;
    }

    @Override

    public void initData() {
    }

    @Override
    public void initEvent() {
        bindingView.setOnClick(new OnClick());
    }

    public class OnClick {

        public void onClickToUser(View view) {
            ARouter.getInstance().build("/com/UserInfoActivity").navigation();
        }

        public void onClickToLucky(View view) {
            ARouter.getInstance().build("/com/LuckyActivity").navigation();
        }
    }
}
