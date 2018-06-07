package com.lunioussky.armier.main.mui.relax.fragment;


import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.databinding.RelaxFragmentBind;
import com.lunioussky.armier.main.mui.relax.adapter.RelaxFragmentAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 11645 on 2018/3/13.
 */

public class RelaxFragment extends BaseFragment<RelaxFragmentBind> {


    private final List<String> mList = new ArrayList<String>();
    private RelaxFragmentAdapter mAdapter;


    @Override
    public int setContent() {
        return R.layout.fragment_relax;
    }


    @Override
    public void initData() {

        if (mList.size() > 0) {
            mList.clear();
        }
        mList.add("新鲜事");
        mList.add("无聊图");
        mList.add("妹子图");
        mList.add("段子");

        mAdapter = new RelaxFragmentAdapter(mList, getFragmentManager());
        bindingView.vpView.setAdapter(mAdapter);
        bindingView.tabLayout.setupWithViewPager(bindingView.vpView);

    }

    @Override
    public void initEvent() {

        for (int i = 0; i < bindingView.tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = bindingView.tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(i));
            }

        }

        updateTabTextView(bindingView.tabLayout.getTabAt(bindingView.tabLayout.getSelectedTabPosition()), true);


        bindingView.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        TextView select = tab.getCustomView().findViewById(R.id.tab_item_textview);

        if (isSelect) {
            //选中加粗
            select.setText(tab.getText());
            select.setTextSize(20);
            select.setTextColor(getResources().getColor(R.color.red));
        } else {
            select.setText(tab.getText());
            select.setTextSize(16);
            select.setTextColor(getResources().getColor(R.color.tab_normal_color));

        }


    }
}