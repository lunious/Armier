package com.lunioussky.armier.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

/**
 * Author: lunious
 * Date: 2018/7/14 21:01
 * Description:
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    public MainFragmentPagerAdapter(List<Fragment> fragments,FragmentManager fm) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
