package com.lunioussky.armier.base;

import android.os.Handler;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lunioussky.armier.R;
import com.lunioussky.armier.databinding.LauncherFragmentBind;
import com.lunioussky.armier.main.view.GlideApp;
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
        GlideApp.with(this)
                .load("http://api.dujin.org/bing/1920.php")
                .placeholder(R.mipmap.ic_personal_bg)
                .centerCrop()
                .into(bindingView.ivAd);
        GlideApp.with(this)
                .asGif()
                .load(R.drawable.newlogo)
                .into(bindingView.gifImageView);

    }

    @Override
    public void initEvent() {
        //暂定休眠3秒跳转首页，日后根据业务修改
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toMain();
            }
        }, 3000);
        //如果点击了"跳过"，立即跳转到首页
        bindingView.tvSkip.setOnClickListener(new PerfectClickListener() {
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
