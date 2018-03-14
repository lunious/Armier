package com.lunioussky.armier.ui.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lunioussky.armier.R;

import java.util.ArrayList;

/**
 * Created by 11645 on 2018/3/13.
 */

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }


    ArrayList<String> tabList = new ArrayList<>();

    private void initView() {
        //引用tablayout

        tabList.add("热点");
        tabList.add("娱乐");
        tabList.add("体育");
        tabList.add("最新");
        tabList.add("推荐");
        tabList.add("八卦");
        tabList.add("热点");
        tabList.add("娱乐");
        tabList.add("体育");
        tabList.add("最新");
        tabList.add("推荐");
        tabList.add("八卦");


        TabLayout tabLayout = getView().findViewById(R.id.tab_layout);

        for (int i = 0; i < tabList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabList.get(i)));
        }


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
        textView.setText(tabList.get(currentPosition));
        return view;
    }

    private void updateTabTextView(TabLayout.Tab tab, boolean isSelect) {

        if (isSelect) {
            //选中加粗
            TextView tabSelect = tab.getCustomView().findViewById(R.id.tab_item_textview);
            tabSelect.setText(tab.getText());
            tabSelect.setTextSize(18);
            tabSelect.setTextColor(getResources().getColor(R.color.red));
        } else {
            TextView tabUnSelect = tab.getCustomView().findViewById(R.id.tab_item_textview);
            tabUnSelect.setText(tab.getText());
            tabUnSelect.setTextSize(16);
            tabUnSelect.setTextColor(getResources().getColor(R.color.tab_normal_color));
        }
    }
}
