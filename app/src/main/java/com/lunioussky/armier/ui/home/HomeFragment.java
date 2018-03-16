package com.lunioussky.armier.ui.home;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11645 on 2018/3/13.
 */

public class HomeFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager resultVp = null;

    private final List<String> mList = new ArrayList<String>();
    private ResultFragmentAdapter mAdapter;


    @Override
    public Object setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        tabLayout = getView().findViewById(R.id.tab_layout);
        resultVp = getView().findViewById(R.id.vp_view);
    }

    @Override
    public void initData() {
        mList.add("最新标讯");
        mList.add("勘察");
        mList.add("设计");
        mList.add("施工");
        mList.add("新闻");
        mList.add("政府采购");
        mList.add("采购");
        mList.add("行业动态");
        mList.add("污水");

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
