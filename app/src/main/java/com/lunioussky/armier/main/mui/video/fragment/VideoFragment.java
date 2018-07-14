package com.lunioussky.armier.main.mui.video.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lunioussky.armier.R;
import com.lunioussky.armier.api.JyApi;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.main.mui.video.adapter.VideoFragmentAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by 11645 on 2018/3/13.
 */

public class VideoFragment extends BaseFragment {
    private final List<String> mList = new ArrayList<String>();
    private final List<String> mId = new ArrayList<String>();
    @BindView(R.id.tab_video)
    TabLayout tabVideo;
    @BindView(R.id.vp_video)
    ViewPager vpVideo;
    private VideoFragmentAdapter mAdapter;
    private boolean isInitCache = false;


    public void requestData() {
        OkGo.<String>post(JyApi.iFeng)
                .cacheKey("video_list_tab_cache")
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .cacheTime(3600 * 72000)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final JSONArray jsonArray = JSON.parseArray(response.body());
                        final JSONObject object = jsonArray.getJSONObject(0);
                        final JSONArray array = object.getJSONArray("types");
                        if (mList.size() > 0) {
                            mList.clear();
                        }
                        for (int i = 0; i < array.size(); i++) {
                            final JSONObject jsonObject = array.getJSONObject(i);
                            final String name = jsonObject.getString("name");
                            final String id = jsonObject.getString("id");
                            mList.add(name);
                            mId.add(id);
                        }
                        mAdapter = new VideoFragmentAdapter(mList, mId, getFragmentManager());
                        vpVideo.setAdapter(mAdapter);
                        tabVideo.setupWithViewPager(vpVideo);

                        initTab();


                    }

                    @Override
                    public void onCacheSuccess(Response<String> response) {
                        if (!isInitCache) {
                            final JSONArray jsonArray = JSON.parseArray(response.body());
                            final JSONObject object = jsonArray.getJSONObject(0);
                            final JSONArray array = object.getJSONArray("types");
                            if (mList.size() > 0) {
                                mList.clear();
                            }
                            for (int i = 0; i < array.size(); i++) {
                                final JSONObject jsonObject = array.getJSONObject(i);
                                final String name = jsonObject.getString("name");
                                final String id = jsonObject.getString("id");
                                mList.add(name);
                                mId.add(id);
                            }
                            mAdapter = new VideoFragmentAdapter(mList, mId, getFragmentManager());
                            vpVideo.setAdapter(mAdapter);
                            tabVideo.setupWithViewPager(vpVideo);

                            Log.d("JASDUYHASDASDSAD", mList.toString());
                            initTab();

                            isInitCache = true;
                        }

                    }
                });


    }


    public void initTab() {
        for (int i = 0; i < tabVideo.getTabCount(); i++) {
            TabLayout.Tab tab = tabVideo.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(i));
            }

        }

        updateTabTextView(tabVideo.getTabAt(tabVideo.getSelectedTabPosition()), true);

        tabVideo.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        requestData();
    }

    @Override
    protected void initEnvent(Bundle savedInstanceState) {

    }

}
