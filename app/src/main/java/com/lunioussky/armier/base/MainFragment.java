package com.lunioussky.armier.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lunioussky.armier.R;
import com.lunioussky.armier.main.mui.index.fragment.IndexFragment;
import com.lunioussky.armier.main.mui.lab.fragment.LabFragment;
import com.lunioussky.armier.main.mui.relax.fragment.RelaxFragment;
import com.lunioussky.armier.main.mui.video.fragment.VideoFragment;
import com.lunioussky.armier.main.view.bottomBar.BottomBar;
import com.lunioussky.armier.main.view.bottomBar.BottomBarTab;

import butterknife.BindView;


/**
 * Author: lunious
 * Date: 2018/7/15 1:24
 * Description:
 */
public class MainFragment extends BaseFragment {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUR = 3;
    @BindView(R.id.main_tab_container)
    FrameLayout mainTabContainer;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;


    private BaseFragment[] mFragments = new BaseFragment[4];


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BaseFragment firstFragment = findChildFragment(IndexFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = new IndexFragment();
            mFragments[SECOND] = new VideoFragment();
            mFragments[THIRD] = new RelaxFragment();
            mFragments[FOUR] = new LabFragment();

            loadMultipleRootFragment(R.id.main_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOUR]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findChildFragment(VideoFragment.class);
            mFragments[THIRD] = findChildFragment(RelaxFragment.class);
            mFragments[FOUR] = findChildFragment(LabFragment.class);
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        bottomBar
                .addItem(new BottomBarTab(_mActivity, R.mipmap.tab_home_normal, getString(R.string.index)))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.tab_video_normal, getString(R.string.video)))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.tab_micro_normal, getString(R.string.relax)))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.tab_lab_normal, getString(R.string.lab)));


        bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

    }

    @Override
    protected void initEnvent(Bundle savedInstanceState) {

    }

}
