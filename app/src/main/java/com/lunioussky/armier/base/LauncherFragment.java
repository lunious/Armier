package com.lunioussky.armier.base;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lunioussky.armier.R;
import com.lunioussky.armier.databinding.LauncherFragmentBind;
import com.lunioussky.armier.util.PerfectClickListener;

import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Author: lunious
 * Date: 2018/6/6 9:39
 * Description:
 */
public class LauncherFragment extends BaseFragment {
    public LauncherFragmentBind bind;
    private Boolean isIn = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_launcher, container, false);
        return bind.getRoot();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        //暂定休眠4秒跳转首页，日后根据业务修改
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toMain();
            }
        }, 4000);
        //如果点击了"跳过"，立即跳转到首页
        bind.tvJump.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                toMain();
            }
        });
    }

    private void toMain() {
        if (isIn) {
            return;
        }
        ARouter.getInstance().build("/com/MainActivity").withTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out).navigation(getActivity());
        isIn = true;
        getActivity().finish();
    }

}
