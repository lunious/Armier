package com.lunioussky.armier.main.mui.video.fragment;


import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lunioussky.armier.R;
import com.lunioussky.armier.api.JyApi;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.databinding.VideoFragmentBind;
import com.lunioussky.armier.main.mui.video.adapter.VideoFragmentAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by 11645 on 2018/3/13.
 */

public class VideoFragment extends BaseFragment<VideoFragmentBind> {
    private final List<String> mList = new ArrayList<String>();
    private VideoFragmentAdapter mAdapter;

    @Override
    public int setContent() {
        return R.layout.fragment_video;
    }

    @Override
    public void initData() {
        requestData();
    }

    public void requestData() {
        OkGo.<String>post(JyApi.iFeng)
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
                            mList.add(name);
                        }
                        mAdapter = new VideoFragmentAdapter(mList, getFragmentManager());
                        bindingView.vpView.setAdapter(mAdapter);
                        bindingView.tabLayout.setupWithViewPager(bindingView.vpView);

                    }
                });
    }

    @Override
    public void initEvent() {
        for (int i = 0; i < bindingView.tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = bindingView.tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(i));
            }

        }

//        updateTabTextView(bindingView.tabLayout.getTabAt(bindingView.tabLayout.getSelectedTabPosition()), true);


        bindingView.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                updateTabTextView(tab, true);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                updateTabTextView(tab, false);
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
