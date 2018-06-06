package com.lunioussky.armier.base;

import android.os.Handler;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.lunioussky.armier.R;
import com.lunioussky.armier.databinding.LauncherFragmentBind;
import com.lunioussky.armier.util.PerfectClickListener;

/**
 * Author: lunious
 * Date: 2018/6/6 9:39
 * Description:
 */
public class LauncherFragment extends BaseFragment<LauncherFragmentBind> {
    private Boolean isIn = false;


    @Override
    public int setContent() {
        return R.layout.fragment_launcher;
    }

    @Override
    public void initData() {
        //加载必应每日一图
        Glide.with(this)
                .load("https://www.xwboke.cn/api/api.php")
                .into(bindingView.ivPic);
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
        bindingView.tvJump.setOnClickListener(new PerfectClickListener() {
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


    //闪页不可退出
    @Override
    public boolean onBackPressedSupport() {
        return true;
    }
}
