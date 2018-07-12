package com.lunioussky.armier.main.mui.index.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;


/**
 * Author: lunious
 * Date: 2018/7/12 16:51
 * Description:
 */
public class IndexFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mList = new ArrayList<>();
    private ArrayList<Fragment> mFragment = new ArrayList<>();
    public IndexFragmentAdapter(ArrayList<String> list,FragmentManager fm) {
        super(fm);
        this.mList = list;

        for (int i = 0; i < mList.size(); i++) {
            mFragment.add(IndexListFragment.getInstance(mList.get(i)));
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
