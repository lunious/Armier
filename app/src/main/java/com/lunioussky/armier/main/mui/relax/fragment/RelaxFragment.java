package com.lunioussky.armier.main.mui.relax.fragment;


import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lunioussky.armier.R;
import com.lunioussky.armier.api.JyApi;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.databinding.RelaxFragmentBind;
import com.lunioussky.armier.main.mui.relax.adapter.RelaxFragmentAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
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

                            mAdapter = new RelaxFragmentAdapter(mList, getFragmentManager());
                            bindingView.vpView.setAdapter(mAdapter);
                            bindingView.tabLayout.setupWithViewPager(bindingView.vpView);

                            initTab();
                        }

                    }
                });


    }

    public void initTab() {
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

    @Override
    public void initEvent() {

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
