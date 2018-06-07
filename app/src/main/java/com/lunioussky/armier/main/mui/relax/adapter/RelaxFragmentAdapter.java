package com.lunioussky.armier.main.mui.relax.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lunioussky.armier.main.mui.relax.fragment.RelaxBoredListFragment;
import com.lunioussky.armier.main.mui.relax.fragment.RelaxDuanziListFragment;
import com.lunioussky.armier.main.mui.relax.fragment.RelaxMeiziListFragment;

import java.util.List;

/**
 * Created by 11645 on 2018/3/14.
 */

public class RelaxFragmentAdapter extends FragmentPagerAdapter {

    private List<String> mList;

    public RelaxFragmentAdapter(List<String> list, FragmentManager fm) {
        super(fm);
        this.mList = list;

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return RelaxMeiziListFragment.getInstance(mList.get(0));
            case 1:
                return RelaxBoredListFragment.getInstance(mList.get(1));
            case 2:
                return RelaxMeiziListFragment.getInstance(mList.get(2));
            case 3:
                return RelaxDuanziListFragment.getInstance(mList.get(3));
        }
        return null;
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
