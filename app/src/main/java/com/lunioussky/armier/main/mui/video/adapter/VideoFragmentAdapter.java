package com.lunioussky.armier.main.mui.video.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.lunioussky.armier.main.mui.relax.fragment.RelaxFreshListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11645 on 2018/3/14.
 */

public class VideoFragmentAdapter extends FragmentPagerAdapter {

    private List<String> mList = new ArrayList<>();
    private ArrayList<Fragment> mFragment = new ArrayList<>();

    public VideoFragmentAdapter(List<String> list, FragmentManager fm) {
        super(fm);
        this.mList = list;

        for (int i = 0; i < mList.size(); i++) {
            mFragment.add(RelaxFreshListFragment.getInstance(mList.get(i)));
        }

    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position);
    }
}
