package com.lunioussky.armier.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.lunioussky.armier.R;
import com.lunioussky.armier.main.mui.index.fragment.IndexFragment;
import com.lunioussky.armier.main.mui.lab.fragment.LabFragment;
import com.lunioussky.armier.main.mui.relax.fragment.RelaxFragment;
import com.lunioussky.armier.main.mui.video.fragment.VideoFragment;
import com.lunioussky.armier.main.view.bottomBar.BottomBar;
import com.lunioussky.armier.main.view.bottomBar.BottomBarTab;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 11645 on 2018/3/13.
 */

@Route(path = "/com/MainActivity")
public class MainActivity extends BaseActivity {

    @BindView(R.id.vp_main)
    NoScrollViewPager vpMain;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;


    //再按一次退出程序
    private static final long WAIT_TIME = 3000L;
    private long TOUCH_TIME = 0;
    private MainFragmentPagerAdapter mPagerAdapter;

    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // 在全屏或者小窗口时按返回键要先退出全屏或小窗口，
        // 所以在Activity中onBackPress要交给NiceVideoPlayer先处理。
        if (NiceVideoPlayerManager.instance().onBackPressd()) return;

        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        bottomBar
                .addItem(new BottomBarTab(this, R.mipmap.tab_home_normal, getString(R.string.index)))
                .addItem(new BottomBarTab(this, R.mipmap.tab_video_normal, getString(R.string.video)))
                .addItem(new BottomBarTab(this, R.mipmap.tab_micro_normal, getString(R.string.relax)))
                .addItem(new BottomBarTab(this, R.mipmap.tab_lab_normal, getString(R.string.lab)));

        mPagerAdapter = new MainFragmentPagerAdapter(getFragments(), getSupportFragmentManager());
        vpMain.setOffscreenPageLimit(4);//设置默认预加载个数 默认是1
        vpMain.setNoScroll(true);
        vpMain.setAdapter(mPagerAdapter);

    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new IndexFragment());
        fragments.add(new VideoFragment());
        fragments.add(new RelaxFragment());
        fragments.add(new LabFragment());
        return fragments;
    }

    @Override
    protected void initEvent(Bundle savedInstanceState) {
        vpMain.setCurrentItem(0);//默认显示第一页

        bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                vpMain.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
                mPagerAdapter.notifyDataSetChanged();
                vpMain.setCurrentItem(position);
            }
        });
    }

}
