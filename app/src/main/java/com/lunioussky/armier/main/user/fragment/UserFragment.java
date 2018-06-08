package com.lunioussky.armier.main.user.fragment;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.databinding.UserFragmentBind;

/**
 * Author: lunious
 * Date: 2018/6/8 16:52
 * Description:
 */
public class UserFragment extends BaseFragment<UserFragmentBind> {
    @Override
    public int setContent() {
        return R.layout.fragment_user;
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

    }
}
