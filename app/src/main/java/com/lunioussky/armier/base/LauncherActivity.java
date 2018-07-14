package com.lunioussky.armier.base;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lunioussky.armier.R;
import com.lunioussky.armier.main.view.GlideApp;
import com.lunioussky.armier.util.PerfectClickListener;

import butterknife.BindView;

/**
 * Author: lunious
 * Date: 2018/6/6 9:38
 * Description:
 */
public class LauncherActivity extends BaseActivity {

    @BindView(R.id.iv_ad)
    ImageView ivAd;
    @BindView(R.id.gifImageView)
    ImageView gifImageView;
    @BindView(R.id.ll_bottom)
    RelativeLayout llBottom;
    @BindView(R.id.tv_skip)
    TextView tvSkip;
    private Boolean isIn = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launcher;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //加载必应每日一图
        GlideApp.with(this)
                .load("http://api.dujin.org/bing/1920.php")
                .centerCrop()
                .into(ivAd);
        GlideApp.with(this)
                .asGif()
                .load(R.drawable.newlogo)
                .into(ivAd);
    }

    @Override
    protected void initEvent(Bundle savedInstanceState) {
        //暂定休眠3秒跳转首页，日后根据业务修改
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toMain();
            }
        }, 3000);
        //如果点击了"跳过"，立即跳转到首页
        tvSkip.setOnClickListener(new PerfectClickListener() {
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
        ARouter.getInstance().build("/com/MainActivity").navigation(this);
        isIn = true;
        finish();
    }


}
