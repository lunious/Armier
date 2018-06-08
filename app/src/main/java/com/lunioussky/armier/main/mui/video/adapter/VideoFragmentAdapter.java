package com.lunioussky.armier.main.mui.video.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.lunioussky.armier.main.mui.video.fragment.VideoListFragment;
import java.util.ArrayList;
import java.util.List;

 /**
 * Created by 11645 on 2018/3/14.
 */

public class VideoFragmentAdapter extends FragmentPagerAdapter {

    private List<String> mList = new ArrayList<>();
    private List<String> mId = new ArrayList<>();
    private ArrayList<Fragment> mFragment = new ArrayList<>();

    public VideoFragmentAdapter(List<String> list, List<String> id, FragmentManager fm) {
        super(fm);
        this.mList = list;
        this.mId = id;

        for (int i = 0; i < mList.size(); i++) {
            mFragment.add(VideoListFragment.getInstance(mList.get(i), mId.get(i)));
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
