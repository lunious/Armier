package com.lunioussky.armier.main.mui.index.fragment;

import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
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

    }

    @Override
    public void initEvent() {
        bindingView.setOnClick(new OnClick());
    }
    public class OnClick {

        public void onClickToUser(View view) {
            ARouter.getInstance().build("/com/UserActivity").navigation();
        }

    }


}
