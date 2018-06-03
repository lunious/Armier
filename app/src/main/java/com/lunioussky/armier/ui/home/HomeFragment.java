package com.lunioussky.armier.ui.home;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lunioussky.armier.R;
import com.lunioussky.armier.main.BaseFragment;
import com.lunioussky.armier.databinding.HomeFragmentBind;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 11645 on 2018/3/13.
 */

public class HomeFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager resultVp;

    private final List<String> mList = new ArrayList<String>();
    private ResultFragmentAdapter mAdapter;

    private HomeFragmentBind bind;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return bind.getRoot();
    }


    @Override
    public void initData() {
        resultVp = bind.vpView;
        tabLayout = bind.tabLayout;

        if (mList.size() > 0) {
            mList.clear();
        }

        mList.add("热点");
        mList.add("段子");
        mList.add("视频");
        mList.add("图片");

        mAdapter = new ResultFragmentAdapter(mList, getFragmentManager());
        resultVp.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(resultVp);

    }

    @Override
    public void initEvent() {

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(i));
            }

        }

        updateTabTextView(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()), true);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateTabTextView(tab, true);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                updateTabTextView(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    private View getTabView(int currentPosition) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.tab_item, null);
        TextView textView = view.findViewById(R.id.tab_item_textview);
        textView.setText(mList.get(currentPosition));
        return view;
    }

    private void updateTabTextView(TabLayout.Tab tab, boolean isSelect) {

        if (isSelect) {
            //选中加粗
            TextView tabSelect = tab.getCustomView().findViewById(R.id.tab_item_textview);
            tabSelect.setText(tab.getText());
            tabSelect.setTextSize(20);
            tabSelect.setTextColor(getResources().getColor(R.color.red));
        } else {
            TextView tabUnSelect = tab.getCustomView().findViewById(R.id.tab_item_textview);
            tabUnSelect.setText(tab.getText());
            tabUnSelect.setTextSize(16);
            tabUnSelect.setTextColor(getResources().getColor(R.color.tab_normal_color));

        }


    }
}
