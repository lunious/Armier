package com.lunioussky.armier.main.mui.index.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lunioussky.armier.main.mui.index.fragment.IndexListFragment;


import java.util.List;

/**
 * Created by 11645 on 2018/3/14.
 */

public class IndexFragmentAdapter extends FragmentPagerAdapter {

    private List<String> mList;

    public IndexFragmentAdapter(List<String> list, FragmentManager fm) {
        super(fm);
        this.mList = list;

    }

    @Override
    public Fragment getItem(int position) {

        return IndexListFragment.getInstance(mList.get(position));
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
