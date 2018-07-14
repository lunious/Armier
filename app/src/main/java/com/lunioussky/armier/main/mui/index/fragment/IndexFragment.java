package com.lunioussky.armier.main.mui.index.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lunioussky.armier.R;
import com.lunioussky.armier.api.JyApi;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.main.mui.index.adapter.IndexFragmentAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 11645 on 2018/3/13.
 */

public class IndexFragment extends BaseFragment {

    private final List<String> mList = new ArrayList<String>();
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp_view)
    ViewPager vpView;
    @BindView(R.id.iv_portrait)
    ImageView ivPortrait;


    private IndexFragmentAdapter mAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_index;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        OkGo.<String>get(JyApi.indexTab)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final JSONObject object = JSON.parseObject(response.body());
                        final JSONArray array = object.getJSONArray("results");
                        if (array.size() > 0) {
                            if (mList.size() > 0) {
                                mList.clear();
                            }
                            for (int i = 0; i < array.size(); i++) {
                                final JSONObject jsonObject = array.getJSONObject(i);
                                final String name = jsonObject.getString("tabName");
                                if (!TextUtils.isEmpty(name)) {
                                    mList.add(name);
                                }
                            }

                            mAdapter = new IndexFragmentAdapter(mList, getFragmentManager());
                            vpView.setAdapter(mAdapter);
                            tabLayout.setupWithViewPager(vpView);

                            initTab();
                        }

                    }
                });
    }

    @Override
    protected void initEnvent(Bundle savedInstanceState) {

    }


    @butterknife.OnClick(R.id.iv_portrait)
    public void onViewClicked() {
        ARouter.getInstance().build("/com/UserActivity").navigation();
    }


    public void initTab() {
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
