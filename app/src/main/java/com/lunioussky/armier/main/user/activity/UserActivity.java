package com.lunioussky.armier.main.user.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author: lunious
 * Date: 2018/6/8 16:51
 * Description:
 */
@Route(path = "/com/UserActivity")
public class UserActivity extends BaseActivity {

    @BindView(R.id.head_portrait)
    CircleImageView headPortrait;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_type)
    TextView userType;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent(Bundle savedInstanceState) {

    }


    @OnClick(R.id.head_portrait)
    public void onViewClicked() {
        ARouter.getInstance().build("/com/UserInfoActivity").navigation();
    }
}
