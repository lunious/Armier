package com.lunioussky.armier.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.lunioussky.armier.R;
import com.lunioussky.armier.main.view.bottomBar.BottomBarItem;
import com.lunioussky.armier.main.view.bottomBar.BottomBarLayout;
import com.lunioussky.armier.databinding.MainFragmentBind;
import com.lunioussky.armier.main.mui.relax.fragment.RelaxFragment;
import com.lunioussky.armier.main.mui.mine.fragment.MineFragment;
import com.lunioussky.armier.main.mui.index.fragment.IndexFragment;
import com.lunioussky.armier.main.mui.video.fragment.VideoFragment;


/**
 * Created by 11645 on 2018/3/16.
 */

public class MainFragment extends BaseFragment<MainFragmentBind> {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUR = 3;

    private BaseFragment[] mFragments = new BaseFragment[4];

    private BottomBarLayout mBottomBarLayout;
    private RotateAnimation mRotateAnimation;
    private Handler mHandler = new Handler();


    @Override
    public int setContent() {
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
            mFragments[FOUR] = new MineFragment();

            loadMultipleRootFragment(R.id.fl_content, FIRST,
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
            mFragments[FOUR] = findChildFragment(MineFragment.class);
        }

    }

    @Override
    public void initData() {
        mBottomBarLayout = getView().findViewById(R.id.bbl);
    }

    @Override
    public void initEvent() {
        mBottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {

            @Override
            public void onItemSelected(final BottomBarItem bottomBarItem, int previousPosition, final int currentPosition) {

                showHideFragment(mFragments[currentPosition], mFragments[previousPosition]);

                if (currentPosition == 0) {
                    //如果是第一个，即首页
                    if (previousPosition == currentPosition) {
                        //如果是在原来位置上点击,更换首页图标并播放旋转动画
                        bottomBarItem.setIconSelectedResourceId(R.mipmap.tab_loading);
                        bottomBarItem.setStatus(true);

                        //播放旋转动画
                        if (mRotateAnimation == null) {
                            mRotateAnimation = new RotateAnimation(0, 360,
                                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                                    0.5f);
                            mRotateAnimation.setDuration(800);
                            mRotateAnimation.setRepeatCount(-1);
                        }
                        ImageView bottomImageView = bottomBarItem.getImageView();
                        bottomImageView.setAnimation(mRotateAnimation);
                        //播放旋转动画
                        bottomImageView.startAnimation(mRotateAnimation);

                        //模拟数据刷新完毕
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //是否还停留在当前页签
                                boolean tabNotChanged = mBottomBarLayout.getCurrentItem() == currentPosition;
                                //更换成首页原来选中图标
                                bottomBarItem.setIconSelectedResourceId(R.mipmap.tab_home_selected);
                                //刷新图标
                                bottomBarItem.setStatus(tabNotChanged);
                                cancelTabLoading(bottomBarItem);
                            }
                        }, 3000);
                        return;
                    }
                }

                //如果点击了其他条目
                BottomBarItem bottomItem = mBottomBarLayout.getBottomItem(0);
                //更换为原来的图标
                bottomItem.setIconSelectedResourceId(R.mipmap.tab_home_selected);
                //停止旋转动画
                cancelTabLoading(bottomItem);

            }
        });

        //设置显示提示的小红点
//        mBottomBarLayout.showNotify(3);
    }

    /**
     * 停止首页页签的旋转动画
     */
    private void cancelTabLoading(BottomBarItem bottomItem) {
        Animation animation = bottomItem.getImageView().getAnimation();
        if (animation != null) {
            animation.cancel();
        }
    }


}
