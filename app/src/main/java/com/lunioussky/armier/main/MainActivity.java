package com.lunioussky.armier.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseActivity;
import com.lunioussky.armier.bottomBar.BottomBarItem;
import com.lunioussky.armier.bottomBar.BottomBarLayout;
import com.lunioussky.armier.ui.home.HomeFragment;
import com.lunioussky.armier.ui.mine.MineFragment;
import com.lunioussky.armier.ui.video.VideoFragment;
import com.lunioussky.armier.ui.news.NewsFragment;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.ISupportActivity;

/**
 * Created by 11645 on 2018/3/13.
 */

public class MainActivity extends BaseActivity {

    private List<Fragment> mFragment = new ArrayList<>();
    private BottomBarLayout mBottomBarLayout;
    private RotateAnimation mRotateAnimation;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        mBottomBarLayout = findViewById(R.id.bbl);
    }

    private void initData() {
        mFragment.add(new HomeFragment());
        mFragment.add(new VideoFragment());
        mFragment.add(new NewsFragment());
        mFragment.add(new MineFragment());

        // 默认显示第一页
        changeFragment(0);
    }


    private void initListener() {
        mBottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final BottomBarItem bottomBarItem, int previousPosition, final int currentPosition) {

                changeFragment(currentPosition);

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

    private void changeFragment(int currentPosition) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_content, mFragment.get(currentPosition));
        transaction.commit();
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
