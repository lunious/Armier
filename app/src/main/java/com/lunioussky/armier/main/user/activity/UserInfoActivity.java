package com.lunioussky.armier.main.user.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseActivity;


/**
 * Author: lunious
 * Date: 2018/6/3 12:45
 * Description:
 */
@Route(path = "/com/UserInfoActivity")
public class UserInfoActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent(Bundle savedInstanceState) {

    }
}
